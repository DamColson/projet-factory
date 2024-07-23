package ajc.formation.projet_factory.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CompteRequest {

	@NotBlank
	private String login;
	@NotBlank
	private String role;	
	@NotBlank
	private String password;
	
	public CompteRequest() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
