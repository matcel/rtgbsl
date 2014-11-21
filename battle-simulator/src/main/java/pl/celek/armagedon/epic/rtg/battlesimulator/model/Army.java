package pl.celek.armagedon.epic.rtg.battlesimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Army {

	private String name;
	
	private List<Unit[]> unitList = new ArrayList<Unit[]>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Unit[]> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<Unit[]> unitList) {
		this.unitList = unitList;
	}
	
	
}
