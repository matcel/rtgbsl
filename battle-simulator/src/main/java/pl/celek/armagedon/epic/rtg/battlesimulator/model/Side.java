package pl.celek.armagedon.epic.rtg.battlesimulator.model;

import java.util.List;

public class Side {

	private int id;
	private String name;
	
	private List<Army> armyList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Army> getArmyList() {
		return armyList;
	}

	public void setArmyList(List<Army> armyList) {
		this.armyList = armyList;
	}
	
	
}
