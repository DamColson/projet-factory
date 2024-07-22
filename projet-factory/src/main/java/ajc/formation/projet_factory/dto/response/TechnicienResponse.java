package ajc.formation.projet_factory.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;
import ajc.formation.projet_factory.model.Technicien;

public class TechnicienResponse {

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
	@JsonView(CustomJsonViews.TechnicienWithAttributes.class)
	private OrdinateurResponse ordinateurResponse;
	@JsonView(CustomJsonViews.TechnicienWithAttributes.class)
	private CompteResponse compteResponse;
	
	public TechnicienResponse() {
	}
	
	public TechnicienResponse(Technicien technicien) {
		BeanUtils.copyProperties(technicien, this);
		this.ordinateurResponse = new OrdinateurResponse(technicien.getOrdinateur());
		this.compteResponse = new CompteResponse(technicien.getCompte());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public OrdinateurResponse getOrdinateurResponse() {
		return ordinateurResponse;
	}

	public void setOrdinateurResponse(OrdinateurResponse ordinateurResponse) {
		this.ordinateurResponse = ordinateurResponse;
	}

	public CompteResponse getCompteResponse() {
		return compteResponse;
	}

	public void setCompteResponse(CompteResponse compteResponse) {
		this.compteResponse = compteResponse;
	}

	

	
	
}
