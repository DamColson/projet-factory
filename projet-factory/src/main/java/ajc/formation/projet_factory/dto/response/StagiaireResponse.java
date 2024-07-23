package ajc.formation.projet_factory.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Stagiaire;

public class StagiaireResponse {

	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String nom;
	@JsonView(CustomJsonViews.Common.class)
	private String prenom;
	@JsonView(CustomJsonViews.Common.class)
	private String telephone;
	@JsonView(CustomJsonViews.Common.class)
	private String mail;
	@JsonView(CustomJsonViews.StagiaireWithAttributes.class)
	private CompteResponse compteResponse;
	@JsonView(CustomJsonViews.StagiaireWithAttributes.class)
	private OrdinateurResponse ordinateurResponse;
	@JsonView(CustomJsonViews.StagiaireWithAttributes.class)
	private FormationResponse formationResponse;

	public StagiaireResponse() {
	}

	public StagiaireResponse(Stagiaire stagiaire,boolean bool) {
		BeanUtils.copyProperties(stagiaire, this);
		if(bool) {
			if(stagiaire.getCompte()!=null) {
				this.compteResponse = new CompteResponse(stagiaire.getCompte(),false);
			}
			if(stagiaire.getOrdinateur()!=null) {
				this.ordinateurResponse = new OrdinateurResponse(stagiaire.getOrdinateur(),false);
			}
			if(stagiaire.getFormation()!=null) {
				this.formationResponse = new FormationResponse(stagiaire.getFormation(),false);
			}
		}		
	}
	
	public StagiaireResponse(Stagiaire stagiaire) {
		this(stagiaire,true);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public CompteResponse getCompteResponse() {
		return compteResponse;
	}

	public void setCompteResponse(CompteResponse compteResponse) {
		this.compteResponse = compteResponse;
	}

	public OrdinateurResponse getOrdinateurResponse() {
		return ordinateurResponse;
	}

	public void setOrdinateurResponse(OrdinateurResponse ordinateurResponse) {
		this.ordinateurResponse = ordinateurResponse;
	}

	public FormationResponse getFormationResponse() {
		return formationResponse;
	}

	public void setFormationResponse(FormationResponse formationResponse) {
		this.formationResponse = formationResponse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}
