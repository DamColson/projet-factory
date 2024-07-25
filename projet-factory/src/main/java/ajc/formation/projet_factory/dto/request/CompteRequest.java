package ajc.formation.projet_factory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CompteRequest {

	@NotBlank	
	private String login;
	@NotBlank
	private String role;	
	@NotBlank
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,}$",
	message = "Le mot de passe doit contenir 8 caractère, une majuscule, une minuscule, un chiffre et un caractère spécial")
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
