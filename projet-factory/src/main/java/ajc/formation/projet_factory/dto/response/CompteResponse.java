package ajc.formation.projet_factory.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Compte;


public class CompteResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String login;
	@JsonView(CustomJsonViews.Common.class)
	private String role;
	@JsonView(CustomJsonViews.CompteWithPerson.class)
	private TechnicienResponse technicienResponse;
	@JsonView(CustomJsonViews.CompteWithPerson.class)
	private GestionnaireResponse gestionnaireResponse;
	@JsonView(CustomJsonViews.CompteWithPerson.class)
	private FormateurResponse formateurResponse;
	@JsonView(CustomJsonViews.CompteWithPerson.class)
	private StagiaireResponse stagiaireResponse;
	
	public CompteResponse() {
	}
	
	public CompteResponse(Compte compte,boolean bool) {
		BeanUtils.copyProperties(compte, this,"role");
		this.role = compte.getRole().toString();
		if(bool) {
			if(compte.getTechnicien()!=null) {
				this.technicienResponse = new TechnicienResponse(compte.getTechnicien(),false);
			}
			if(compte.getGestionnaire()!=null) {
				this.gestionnaireResponse = new GestionnaireResponse(compte.getGestionnaire(),false);
			}
			if(compte.getFormateur()!=null) {
				this.formateurResponse = new FormateurResponse(compte.getFormateur(),false);
			}
			if(compte.getStagiaire()!=null) {
				this.stagiaireResponse = new StagiaireResponse(compte.getStagiaire(),false);
			}
		}	
	}
	
	public CompteResponse(Compte compte) {
		this(compte,true);
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

	public TechnicienResponse getTechnicienResponse() {
		return technicienResponse;
	}

	public void setTechnicienResponse(TechnicienResponse technicienResponse) {
		this.technicienResponse = technicienResponse;
	}

	public GestionnaireResponse getGestionnaireResponse() {
		return gestionnaireResponse;
	}

	public void setGestionnaireResponse(GestionnaireResponse gestionnaireResponse) {
		this.gestionnaireResponse = gestionnaireResponse;
	}

	public FormateurResponse getFormateurResponse() {
		return formateurResponse;
	}

	public void setFormateurResponse(FormateurResponse formateurResponse) {
		this.formateurResponse = formateurResponse;
	}

	public StagiaireResponse getStagiaireResponse() {
		return stagiaireResponse;
	}

	public void setStagiaireResponse(StagiaireResponse stagiaireResponse) {
		this.stagiaireResponse = stagiaireResponse;
	}
	
		
}
