package ajc.formation.projet_factory.dto.request;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.model.Ordinateur;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TechnicienRequest {

	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	private String telephone;
	@NotBlank
	private String mail;	
	@NotBlank
	private Integer ordinateurId;
	
	public TechnicienRequest() {
		// TODO Auto-generated constructor stub
	}

	public TechnicienRequest(String nom, String prenom, String telephone, String mail, Integer ordinateurId) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.ordinateurId = ordinateurId;
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

	public Integer getOrdinateurId() {
		return ordinateurId;
	}

	public void setOrdinateurId(Integer ordinateurId) {
		this.ordinateurId = ordinateurId;
	}
	
	
}
