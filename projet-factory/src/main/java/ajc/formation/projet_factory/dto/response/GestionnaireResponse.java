package ajc.formation.projet_factory.dto.response;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Gestionnaire;


public class GestionnaireResponse {
	
	@JsonView(CustomJsonViews.Common.class)
	private String nom;
	@JsonView(CustomJsonViews.Common.class)
	private String prenom;
	@JsonView(CustomJsonViews.Common.class)
	private String telephone;
	@JsonView(CustomJsonViews.Common.class)
	private String mail;
	@JsonView(CustomJsonViews.GestionnaireWithAttributes.class)
	private CompteResponse compteResponse;
	@JsonView(CustomJsonViews.GestionnaireWithAttributes.class)
	private OrdinateurResponse ordinateurResponse;
	@JsonView(CustomJsonViews.GestionnaireWithAttributes.class)
	private Set<FormationResponse> formationsResponse;
	

	public GestionnaireResponse() {
	}

	public GestionnaireResponse(Gestionnaire gestionnaire,boolean bool) {
		BeanUtils.copyProperties(gestionnaire, this);
		if(bool) {
			if(gestionnaire.getCompte()!=null) {
				this.compteResponse = new CompteResponse(gestionnaire.getCompte());
			}
			if(gestionnaire.getOrdinateur()!=null) {
				this.ordinateurResponse = new OrdinateurResponse(gestionnaire.getOrdinateur());
			}
			if(gestionnaire.getFormations()!=null) {
				this.formationsResponse = gestionnaire.getFormations().stream().map(formation->{
					return new FormationResponse(formation,false);
				}).collect(Collectors.toSet());
			}
		}	
	}
	
	public GestionnaireResponse(Gestionnaire gestionnaire) {
		this(gestionnaire,true);
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

	public Set<FormationResponse> getFormationsResponse() {
		return formationsResponse;
	}

	public void setFormationsResponse(Set<FormationResponse> formationsResponse) {
		this.formationsResponse = formationsResponse;
	}
	
	
	
	
}
