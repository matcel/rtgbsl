package pl.celek.armagedon.epic.rtg.battlesimulator.model;

import java.util.List;

public class BattleGroup 
{
	private Unit coreUnit;
	private Unit rangedUnit;
	
	//Jednostki flankuj�ce
	private List<Unit> supportUnit;

	public Unit getCoreUnit() {
		return coreUnit;
	}

	public void setCoreUnit(Unit coreUnit) {
		this.coreUnit = coreUnit;
	}

	public Unit getRangedUnit() {
		return rangedUnit;
	}

	public void setRangedUnit(Unit rangedUnit) {
		this.rangedUnit = rangedUnit;
	}

	public List<Unit> getSupportUnit() {
		return supportUnit;
	}

	public void setSupportUnit(List<Unit> supportUnit) {
		this.supportUnit = supportUnit;
	}
	
	
}
