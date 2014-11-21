package pl.celek.armagedon.epic.rtg.battlesimulator.model;

public class Unit {

	private String uuid;

	private String name;
	private int count;
	private int maxCount;
	private int hp;
	private int maxHp;

	private int lifePoints;
	
	private int attack;
	private int defence;
	private int strength;
	private int toughness;
	private int initiative;
	
	private int multiplyModificator;
	

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getToughness() {
		return toughness;
	}

	public void setToughness(int toughness) {
		this.toughness = toughness;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public int getMultiplyModificator() {
		return multiplyModificator;
	}

	public void setMultiplyModificator(int multiplyModificator) {
		this.multiplyModificator = multiplyModificator;
	}

	@Override
	public String toString() {
		return "Unit [uuid=" + uuid + ", name=" + name + ", count=" + count
				+ ", maxCount=" + maxCount + ", hp=" + hp + ", maxHp=" + maxHp
				+ ", lifePoints=" + lifePoints + ", attack=" + attack
				+ ", defence=" + defence + ", strength=" + strength
				+ ", toughness=" + toughness + ", initiative=" + initiative
				+ ", multiplyModificator=" + multiplyModificator + "]";
	}

	
}
