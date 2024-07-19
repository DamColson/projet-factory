package ajc.formation.projet_factory.dto.request;

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
	@NotNull
	private Integer ordinateurId;
	@NotNull
	private Integer compteId;
	
	public TechnicienRequest() {
	}
	
	public TechnicienRequest(@NotBlank String nom, @NotBlank String prenom, String telephone, @NotBlank String mail,
			@NotNull Integer ordinateurId, @NotNull Integer compteId) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.ordinateurId = ordinateurId;
		this.compteId = compteId;
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

	public Integer getCompteId() {
		return compteId;
	}

	public void setCompteId(Integer compteId) {
		this.compteId = compteId;
	}
	
	
	
	
}
