package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.entities.base.Entity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Class used for storing information about monsters.
 * Monster represents one type of monster (such as troll), not concrete instance (troll Jozef).
 * The properties are meant as average (f.e. average height of troll) for the given monster types.
 *
 * @author Simona Kruppova
 */
@javax.persistence.Entity
public class Monster implements Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false, unique = true)
	private String type;

	@DecimalMin("0.1")
	private BigDecimal height;

	@DecimalMin("0.1")
	private BigDecimal weight;

	@Range(min = 1, max = 10)
	private int agility;

	@Range(min = 1, max = 10)
	private int strength;

	@ManyToMany
	private Set<Weapon> weapons = new HashSet<Weapon>();

	@ManyToMany
	private Set<Area> areas = new HashSet<Area>();

	protected Monster() {
		// required by Hibernate
	}

	public Monster(String type) {
		this.type = type;
	}

	public Monster(String type, BigDecimal height, BigDecimal weight, int agility, int strength) {
		this.type = type;
		this.height = height;
		this.weight = weight;
		this.agility = agility;
		this.strength = strength;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Weapon> getWeapons() {
		return weapons;
	}

	public void addWeapon(Weapon weapon) {
		weapons.add(weapon);
	}

	public Set<Area> getAreas() {
		return areas;
	}

	public void addArea(Area area) {
		areas.add(area);
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Monster)) return false;

		Monster monster = (Monster) o;

		return type.equals(monster.getType());
	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}

	public void removeArea(Area area) {
		areas.remove(area);
	}

	public void removeWeapon(Weapon weapon) {
		weapons.remove(weapon);
	}
}
