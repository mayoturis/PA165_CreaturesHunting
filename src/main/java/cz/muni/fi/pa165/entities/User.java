package cz.muni.fi.pa165.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marek on 25.10.2016.
 */
@Entity
@Table(name = "Users") // User is reserved word in derby
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private int age;

	private String password;

	private String email;

	private Boolean isAdmin;

//	not used for now, weapon entity has to be annotated
//	@ManyToMany
//	@JoinTable(name = "user_weapon")
//	private List<Weapon> weapons;
//
//	public void addWeapon(Weapon weapon) {
//		this.weapons.add(weapon);
//	}

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
}
