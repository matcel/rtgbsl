package pl.celek.armagedon.epic.rtg.battlesimulator.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import pl.celek.armagedon.epic.rtg.battlesimulator.model.BattleGroup;
import pl.celek.armagedon.epic.rtg.battlesimulator.model.Unit;

public class FightSimulatorBuilder implements FightBuilder
{
	private final static String BATTLEGROUP_CANNOT_BE_NULL = "Battlegroup object cannot be null"; 
	private final static String BATTLEGROUP_MIN_SIZE = "Battlegroup minimal size is 2";
	private final static String CALCULATE_DAMAGE_CANNOT_BE_NULL = "Damage calculate object cannot be null";
	
	private List<BattleGroup> battleGroups = new ArrayList<BattleGroup>();
	private CalculateDamageCommand calculateDamageCommand = null;
	
	public FightSimulatorBuilder setBattleGroup(BattleGroup battleGroup)
	{
		battleGroups.add(battleGroup);
		return this;	
	}
	
	public FightSimulatorBuilder setCalculateDamageCommand(CalculateDamageCommand calculateDamageCommand)
	{
		this.calculateDamageCommand = calculateDamageCommand;
		return this;
	}
	
	@Override
	public void fight() 
	{
		valid();
		
		BattleGroup battleGroupA = battleGroups.get(0);
		BattleGroup battleGroupB = battleGroups.get(1);

		int dmg = 0;
		boolean simultaneousAttack = false;
		
		Unit coreUnitAttacking = null;
		Unit coreUnitInDefence = null;
		
		//Doda� sorta
		TreeMap<Integer, List<Unit>> initiativeMap = createInitiativeSortMap(battleGroups);
		while(initiativeMap.size() != 0)
		{
			Entry<Integer, List<Unit>> topElement = initiativeMap.pollFirstEntry();
			System.out.println("Initiative:" + topElement.getKey() + " " + topElement.getValue());
			
			for (Unit unit : topElement.getValue())
			{
				Unit target = getPairForCombat(unit, battleGroups);
				//System.out.println("Unit: " + unit + " will target : " + target);
			}
		}
		
		//Sprawdzanie inicjatywy
		if(battleGroupA.getCoreUnit().getInitiative() == battleGroupB.getCoreUnit().getInitiative())
		{
			simultaneousAttack = true;
			coreUnitAttacking = battleGroupA.getCoreUnit();	
			coreUnitInDefence = battleGroupB.getCoreUnit();
		}
		else if (battleGroupA.getCoreUnit().getInitiative() > battleGroupB.getCoreUnit().getInitiative())
		{
			coreUnitAttacking = battleGroupA.getCoreUnit();	
			coreUnitInDefence = battleGroupB.getCoreUnit();
		}
		else
		{			
			coreUnitAttacking = battleGroupB.getCoreUnit();
			coreUnitInDefence = battleGroupA.getCoreUnit();
		}
		
		if (simultaneousAttack)
		{
			dmg = calculateDamageCommand.calculateDamage(coreUnitAttacking, coreUnitInDefence);
			dmg = calculateDamageCommand.calculateDamage(coreUnitInDefence, coreUnitAttacking);
			
			dealDmg(coreUnitAttacking, dmg);
			dealDmg(coreUnitInDefence, dmg);
		}
		else 
		{
			//Attack !
			dmg = calculateDamageCommand.calculateDamage(coreUnitAttacking, coreUnitInDefence);
			dealDmg(coreUnitInDefence, dmg);
			
			//Sprawdzanie czy obronca przezyl
			if (coreUnitInDefence.getCount() <= 0)
			{
				return;
			}
			
			//Retribution
			dmg = calculateDamageCommand.calculateDamage(coreUnitInDefence, coreUnitAttacking);
			dealDmg(coreUnitAttacking, dmg);
		}
	}

	private void valid()
	{
		if (battleGroups == null)
		{
			throw new IllegalArgumentException(BATTLEGROUP_CANNOT_BE_NULL);
		}
		
		if (battleGroups.size() < 2)
		{
			throw new IllegalArgumentException(BATTLEGROUP_MIN_SIZE);
		}
		
		if(calculateDamageCommand == null)
		{
			throw new IllegalArgumentException(CALCULATE_DAMAGE_CANNOT_BE_NULL);
		}
	}
		
	private void dealDmg(Unit coreUnitInDefence, int dmg)
	{
		int maxLifePoints = coreUnitInDefence.getHp() + (coreUnitInDefence.getCount() - 1) * coreUnitInDefence.getMaxHp();
		
		if (maxLifePoints <= dmg)
		{
			coreUnitInDefence.setHp(0);
			coreUnitInDefence.setCount(0);
			System.out.println("Unit: " + coreUnitInDefence.getName() + " Umiera tak bardzo");
			return;
		}
		
		double actualLifePoint = maxLifePoints - dmg;
		int count = (int)(((double)actualLifePoint / coreUnitInDefence.getMaxHp()) + 1);
		int hp = (int)((double)actualLifePoint % coreUnitInDefence.getMaxHp());
		
		if(hp == 0)
		{
			count--;
			hp = coreUnitInDefence.getMaxHp();
		}
		
		coreUnitInDefence.setCount(count);
		coreUnitInDefence.setHp(hp);
		
		System.out.println("Unit: " + coreUnitInDefence.getName() + " dosta� tak mocno zosta�o mu " + count + " liczebnosci w tym " + hp + " hp" );
	}
	
	private TreeMap<Integer, List<Unit>> createInitiativeSortMap(List<BattleGroup> battleGroups)
	{
		final HashMap<Integer, List<Unit>> initiativeMap = new HashMap<Integer, List<Unit>>();
		
		for (BattleGroup battleGroup : battleGroups)
		{

			if (battleGroup.getCoreUnit() != null) 
			{
				putUnit(initiativeMap, battleGroup.getCoreUnit());
			}
			
			if (battleGroup.getRangedUnit() != null)
			{
				putUnit(initiativeMap, battleGroup.getRangedUnit());
			}	
		}
		
	     TreeMap<Integer,List<Unit>> sorted_map = new TreeMap<Integer ,List<Unit>>(new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				
				if(o2 > o1)
				{
					return 1;
				}
				
				return -1;
			}	    	 
	     });
	     
	     sorted_map.putAll(initiativeMap);
	     
		return sorted_map;
	}
	
	private void putUnit(HashMap<Integer, List<Unit>> initiativeMap, Unit unit)
	{
		int initiative = unit.getInitiative();
		
		if(initiativeMap.containsKey(initiative))
		{
			List<Unit> unitList = initiativeMap.get(initiative);
			unitList.add(unit);
			initiativeMap.put(initiative, unitList);
		}
		else
		{
			List<Unit> unitList = new ArrayList<Unit>();
			unitList.add(unit);
			initiativeMap.put(initiative, unitList);
		}
	}
	
	private Unit getPairForCombat(Unit unit, List<BattleGroup> battleGroups)
	{
		int unitIsInIndex = -1;
		
		//Search for battleGroup
		for (int i = 0; i < battleGroups.size(); i++)
		{
			Unit coreUnit =  battleGroups.get(i).getCoreUnit();
			
			if (coreUnit != null && coreUnit.getUuid().equals(unit.getUuid()))
			{
				unitIsInIndex = i;
				break;
			}
			
			Unit rangedUnit = battleGroups.get(i).getRangedUnit();
			
			if (rangedUnit != null && rangedUnit.getUuid().equals(unit.getUuid()))
			{
				unitIsInIndex = i;
				break;
			}
		}
		
		for (int i = 0; i < battleGroups.size(); i++)
		{
			if(i != unitIsInIndex)
			{
				return battleGroups.get(i).getCoreUnit();
			}
		}
		
		return null;
	}
	
}
