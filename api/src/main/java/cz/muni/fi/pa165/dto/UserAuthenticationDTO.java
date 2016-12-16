package cz.muni.fi.pa165.dto;

/**
 * @author Marek Turis
 */
public class UserAuthenticationDTO {
	private String email;
	private String password;

	public UserAuthenticationDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public UserAuthenticationDTO() {
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
