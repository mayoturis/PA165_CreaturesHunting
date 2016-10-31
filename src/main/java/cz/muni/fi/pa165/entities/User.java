package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.enums.Gender;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Class used for storing information about user.
 *
 * @author Marek Turis
 */
@Entity
@Table(name = "Users") // User is reserved word in derby
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false, unique = true)
	private String name;

	@DecimalMin("0.1")
	private int age;

	@NotNull
	@Column(nullable = false)
	@Length(min = 6)
	private String password;

	@NotNull
	@Column(nullable = false, unique = true)
	@Email
	private String email;

	private Boolean isAdmin;

	@NotNull
	private Gender gender;

	@ManyToMany
	private Set<Weapon> weapons = new HashSet<Weapon>();

	public void addWeapon(Weapon weapon) {
		this.weapons.add(weapon);
		weapon.addUser(this);
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

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	public int getId() {
		return id;
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
}
