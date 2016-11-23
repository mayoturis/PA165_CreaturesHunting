package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Gender;

/**
 * @author Marek Turis
 */
public class UserDTO {

	private int id;
	private String name;
	private int age;
	private String password;
	private String email;
	private boolean isAdmin;
	private Gender gender;
	// private Set<WeaponDTO> weapons = new HashSet<WeaponDTO>(); todo uncomment when WeaponDTO is created


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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
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
		if (o == null || getClass() != o.getClass()) return false;

		UserDTO userDTO = (UserDTO) o;

		return name != null ? name.equals(userDTO.name) : userDTO.name == null;

	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}

