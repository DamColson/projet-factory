package ajc.formation.projet_factory.dto.response;

import org.springframework.beans.BeanUtils;

import ajc.formation.projet_factory.model.Compte;

public class CompteResponse {

	private Integer id;
	private String login;
	private String role;
	
	public CompteResponse() {
	}
	
	public CompteResponse(Compte compte) {
		BeanUtils.copyProperties(compte, this,"role");
		this.role = compte.getRole().toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		
}
