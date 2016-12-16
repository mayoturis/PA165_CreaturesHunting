package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Ammunition;

import java.util.HashSet;
import java.util.Set;

/**
 * Data transfer object for weapon.
 *
 * @author Ondrej Zeman
 */
public class WeaponDTO {

	private int id;
	private String name;
	private int range;
	private Ammunition ammunition;

	private Set<UserDTO> users = new HashSet<UserDTO>();
	private Set<MonsterDTO> monsters = new HashSet<MonsterDTO>();

	public void addMonster(MonsterDTO monster) {
		this.monsters.add(monster);
		if(!monster.getWeapons().contains(this)) {
			monster.addWeapon(this);
		}
	}

	public void addUser(UserDTO user) {
		this.users.add(user);
		if(!user.getWeapons().contains(this)) {
			user.addWeapon(this);
		}
	}

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

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public Ammunition getAmmunition() {
		return ammunition;
	}

	public void setAmmunition(Ammunition ammunition) {
		this.ammunition = ammunition;
	}

	public Set<UserDTO> getUsers() {
		return users;
	}

	public Set<MonsterDTO> getMonsters() { return monsters; }

	public void addUsers(Set<UserDTO> users) {
		this.users.addAll(users);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof WeaponDTO)) return false;

		WeaponDTO weapon = (WeaponDTO) o;

		return getName().equals(weapon.getName());

	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}
}
