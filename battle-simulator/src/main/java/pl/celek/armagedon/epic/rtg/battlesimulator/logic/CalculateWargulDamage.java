package pl.celek.armagedon.epic.rtg.battlesimulator.logic;


import pl.celek.armagedon.epic.rtg.battlesimulator.model.*;

public class CalculateWargulDamage implements CalculateDamageCommand {

	@Override
	public int calculateDamage(Unit coreUnitAttacking, Unit coreUnitInDefence) 
	{
		double dmg = (coreUnitAttacking.getCount() / 2D) * coreUnitAttacking.getMultiplyModificator()  * ((coreUnitAttacking.getAttack() + 5D) / (coreUnitInDefence.getDefence() + 5D) * ((coreUnitAttacking.getStrength() + 1D)/(coreUnitInDefence.getToughness() + 1D)));
		
		if (dmg < 1)
		{
			dmg = 1;
		}
		
		System.out.println("Unit: " + coreUnitAttacking.getName() + " deal: " + dmg + " dmg to: " + coreUnitInDefence.getName());
		
		return (int) Math.round(dmg);
	}

}
