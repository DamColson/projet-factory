package ajc.formation.projet_factory.dto.request;

import ajc.formation.projet_factory.dto.response.TechnicienResponse;
import jakarta.validation.constraints.NotBlank;

public class CompteRequest {

	@NotBlank
	private String login;
	@NotBlank
	private String role;	
	private Integer technicienId;
	private Integer formateurId;
	private Integer stagiaireId;
	private Integer gestionnaireId;
	
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

	public Integer getTechnicienId() {
		return technicienId;
	}

	public void setTechnicienId(Integer technicienId) {
		this.technicienId = technicienId;
	}

	public Integer getFormateurId() {
		return formateurId;
	}

	public void setFormateurId(Integer formateurId) {
		this.formateurId = formateurId;
	}

	public Integer getStagiaireId() {
		return stagiaireId;
	}

	public void setStagiaireId(Integer stagiaireId) {
		this.stagiaireId = stagiaireId;
	}

	public Integer getGestionnaireId() {
		return gestionnaireId;
	}

	public void setGestionnaireId(Integer gestionnaireId) {
		this.gestionnaireId = gestionnaireId;
	}
	
}
