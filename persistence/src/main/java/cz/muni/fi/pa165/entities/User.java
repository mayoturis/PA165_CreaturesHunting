package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class used for storing information about user.
 *
 * @author Marek Turis
 */
@javax.persistence.Entity
@Table(name = "Users") // User is reserved word in derby
public class User implements cz.muni.fi.pa165.entities.base.Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false, unique = true)
	private String name;

	@Min(0)
	private int age;

	@NotNull
	@Column(nullable = false)
	private String password;

	@NotNull
	@Column(nullable = false, unique = true)
	private String email;

	private boolean isAdmin;

	@Enumerated
	private Gender gender;

	@ManyToMany
	private Set<Weapon> weapons = new HashSet<Weapon>();

	protected User() {
		// required by Hibernate
	}

	public User(String name, String email, String password, int age, Gender gender) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.gender = gender;
	}

	public void addWeapon(Weapon weapon) {
		this.weapons.add(weapon);
	}

	public Set<Weapon> getWeapons() {
		return weapons;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		return name.equals(user.getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public void removeWeapon(Weapon weapon) {
		weapons.remove(weapon);
	}
}
