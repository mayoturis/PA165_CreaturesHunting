package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.enums.Ammunition;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class created to store information about weapon.
 * @author Ondrej Zeman
 */
@javax.persistence.Entity
public class Weapon implements cz.muni.fi.pa165.entities.base.Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false, unique = true)
	private String name;

	@Min(0)
	private int range;

	@NotNull
	private Ammunition ammunition;

	@ManyToMany(mappedBy = "weapons")
	private Set<User> users = new HashSet<User>();

	@ManyToMany(mappedBy = "weapons")
	private Set<Monster> monsters = new HashSet<Monster>();

	protected Weapon() {
		// required by Hibernate
	}

	public Weapon(String name, Ammunition ammunition) {
		this.name = name;
		this.ammunition = ammunition;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Weapon)) return false;

		Weapon weapon = (Weapon) o;

		return getName().equals(weapon.getName());
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public Set<User> getUsers() {
		return users;
	}

	public Set<Monster> getMonsters() {
		return monsters;
	}

	public int getId() {
		return id;
	}

	public Ammunition getAmmunition() {
		return ammunition;
	}

	public void setAmmunition(Ammunition ammunition) {
		this.ammunition = ammunition;
	}

	public void addMonsters(List<Monster> monsters) {
		this.monsters.addAll(monsters);
	}

	public void addMonster(Monster monster) {
		this.monsters.add(monster);
		monster.addWeapon(this);
	}

	public void addUser(User user) {
		this.users.add(user);
		user.addWeapon(this);
	}
}
