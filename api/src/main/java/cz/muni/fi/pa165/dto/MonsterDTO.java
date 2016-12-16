package cz.muni.fi.pa165.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Data transfer object for monster.
 *
 * @author Simona Kruppova
 */
public class MonsterDTO {

	private int id;
	private String type;
	private BigDecimal height;
	private BigDecimal weight;
	private int agility;
	private int strength;

	private Set<WeaponDTO> weapons = new HashSet<WeaponDTO>();
	private Set<AreaDTO> areas = new HashSet<AreaDTO>();

	public void addArea(AreaDTO area) {
		areas.add(area);
		if(!area.getMonsters().contains(this)) {
			area.addMonster(this);
		}
	}

	public void addWeapon(WeaponDTO weapon) {
		weapons.add(weapon);
		if(!weapon.getMonsters().contains(this)) {
			weapon.addMonster(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public Set<WeaponDTO> getWeapons() {
		return weapons;
	}

	public void setWeapons(Set<WeaponDTO> weapons) {
		this.weapons = weapons;
	}

	public Set<AreaDTO> getAreas() { return areas; }

	public void setAreas(Set<AreaDTO> areas) {
		this.areas = areas;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MonsterDTO)) return false;

		MonsterDTO monster = (MonsterDTO) o;

		return type.equals(monster.getType());
	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}
}
