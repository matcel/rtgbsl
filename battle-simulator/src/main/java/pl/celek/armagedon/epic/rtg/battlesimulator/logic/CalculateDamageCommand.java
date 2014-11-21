package pl.celek.armagedon.epic.rtg.battlesimulator.logic;

import pl.celek.armagedon.epic.rtg.battlesimulator.model.*;

public interface CalculateDamageCommand 
{
	public int calculateDamage(Unit coreUnitAttacking, Unit coreUnitInDefence);
}
