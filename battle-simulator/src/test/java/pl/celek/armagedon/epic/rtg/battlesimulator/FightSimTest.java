package pl.celek.armagedon.epic.rtg.battlesimulator;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

import pl.celek.armagedon.epic.rtg.battlesimulator.logic.CalculateWargulDamage;
import pl.celek.armagedon.epic.rtg.battlesimulator.logic.FightSimulatorBuilder;
import pl.celek.armagedon.epic.rtg.battlesimulator.model.BattleGroup;
import pl.celek.armagedon.epic.rtg.battlesimulator.model.Unit;

public class FightSimTest {

	@Test
	public void twoUnitShouldFightUnitOneDie()
	{
		final int EXPECTED_PEASANTS_NUMBER_AFTER_DRAGOON_ENCOUNTER = 0;
		final int EXPECTED_PEASANTS_HP_AFTER_DRAGOON_ENCOUNTER = 0;
		
		Unit genericPeasantsUnit = new Unit();
		genericPeasantsUnit.setName("Wieśniak");
		genericPeasantsUnit.setCount(100);
		genericPeasantsUnit.setMaxCount(100);
		genericPeasantsUnit.setAttack(2);
		genericPeasantsUnit.setDefence(2);
		genericPeasantsUnit.setHp(1);
		genericPeasantsUnit.setInitiative(5);
		genericPeasantsUnit.setMaxHp(1);
		genericPeasantsUnit.setStrength(2);
		genericPeasantsUnit.setToughness(2);
		genericPeasantsUnit.setUuid(UUID.randomUUID().toString());
		genericPeasantsUnit.setLifePoints(genericPeasantsUnit.getCount() * genericPeasantsUnit.getHp());
		genericPeasantsUnit.setMultiplyModificator(1);
		
		Unit awesomePowerTurboDragoonZPiekłAleBezMięsa = new Unit();
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setName("Wypasiony smok zagłady");
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setCount(1);
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setMaxCount(1);
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setAttack(5);
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setDefence(5);
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setHp(50);
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setInitiative(1);
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setMaxHp(50);
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setStrength(5);
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setToughness(5);
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setUuid(UUID.randomUUID().toString());
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setLifePoints(awesomePowerTurboDragoonZPiekłAleBezMięsa.getCount() * awesomePowerTurboDragoonZPiekłAleBezMięsa.getHp());
		awesomePowerTurboDragoonZPiekłAleBezMięsa.setMultiplyModificator(45);
		
		BattleGroup battleGroupA = new BattleGroup();
		battleGroupA.setCoreUnit(genericPeasantsUnit);

		BattleGroup battleGroupB = new BattleGroup();
		battleGroupB.setCoreUnit(awesomePowerTurboDragoonZPiekłAleBezMięsa);
		
		FightSimulatorBuilder fightSimulatorBuilder = new FightSimulatorBuilder();
		
		while ((genericPeasantsUnit.getHp() != 0 || genericPeasantsUnit.getCount() != 0)
				&& (awesomePowerTurboDragoonZPiekłAleBezMięsa.getHp() != 0 || awesomePowerTurboDragoonZPiekłAleBezMięsa.getCount() != 0))
		{
			fightSimulatorBuilder.setBattleGroup(battleGroupA)
								 .setBattleGroup(battleGroupB)
								 .setCalculateDamageCommand(new CalculateWargulDamage())
								 .fight();
		}

		assertEquals(EXPECTED_PEASANTS_NUMBER_AFTER_DRAGOON_ENCOUNTER, genericPeasantsUnit.getCount());
		assertEquals(EXPECTED_PEASANTS_HP_AFTER_DRAGOON_ENCOUNTER, genericPeasantsUnit.getHp());
	}
	
	@Test
	public void twoSameUnitShouldDieTogether()
	{
		final int EXPECTED_PEASANTS_COUNT_AFTER_ENCOUTER = 0;
		final int EXPECTED_PEASANTS_HP_AFTER_ENCOUTNER = 0;
		
		Unit genericPeasantsUnit = new Unit();
		genericPeasantsUnit.setName("Wieęniak");
		genericPeasantsUnit.setCount(100);
		genericPeasantsUnit.setMaxCount(100);
		genericPeasantsUnit.setAttack(2);
		genericPeasantsUnit.setDefence(2);
		genericPeasantsUnit.setHp(1);
		genericPeasantsUnit.setInitiative(5);
		genericPeasantsUnit.setMaxHp(1);
		genericPeasantsUnit.setStrength(2);
		genericPeasantsUnit.setToughness(2);
		genericPeasantsUnit.setUuid(UUID.randomUUID().toString());
		genericPeasantsUnit.setLifePoints(genericPeasantsUnit.getCount() * genericPeasantsUnit.getHp());
		genericPeasantsUnit.setMultiplyModificator(1);
		
		Unit genericPeasantsUnit2 = new Unit();
		genericPeasantsUnit2.setName("Wieśniak");
		genericPeasantsUnit2.setCount(100);
		genericPeasantsUnit2.setMaxCount(100);
		genericPeasantsUnit2.setAttack(2);
		genericPeasantsUnit2.setDefence(2);
		genericPeasantsUnit2.setHp(1);
		genericPeasantsUnit2.setInitiative(5);
		genericPeasantsUnit2.setMaxHp(1);
		genericPeasantsUnit2.setStrength(2);
		genericPeasantsUnit2.setToughness(2);
		genericPeasantsUnit2.setUuid(UUID.randomUUID().toString());
		genericPeasantsUnit2.setLifePoints(genericPeasantsUnit2.getCount() * genericPeasantsUnit2.getHp());
		genericPeasantsUnit2.setMultiplyModificator(1);
		
		BattleGroup battleGroupA = new BattleGroup();
		battleGroupA.setCoreUnit(genericPeasantsUnit);

		BattleGroup battleGroupB = new BattleGroup();
		battleGroupB.setCoreUnit(genericPeasantsUnit2);
		
		FightSimulatorBuilder fightSimulatorBuilder = new FightSimulatorBuilder();
		
		while (genericPeasantsUnit.getHp() != 0 && genericPeasantsUnit2.getHp() != 0)
		{
			fightSimulatorBuilder.setBattleGroup(battleGroupA)
								 .setBattleGroup(battleGroupB)
								 .setCalculateDamageCommand(new CalculateWargulDamage())
								 .fight();
		}
		
		assertEquals(EXPECTED_PEASANTS_COUNT_AFTER_ENCOUTER, genericPeasantsUnit.getCount());
		assertEquals(EXPECTED_PEASANTS_HP_AFTER_ENCOUTNER, genericPeasantsUnit.getCount());
		
		assertEquals(EXPECTED_PEASANTS_COUNT_AFTER_ENCOUTER, genericPeasantsUnit2.getCount());
		assertEquals(EXPECTED_PEASANTS_HP_AFTER_ENCOUTNER, genericPeasantsUnit2.getCount());
	}
	
}
