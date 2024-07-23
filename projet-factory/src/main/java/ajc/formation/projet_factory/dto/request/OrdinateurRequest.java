package ajc.formation.projet_factory.dto.request;

import jakarta.validation.constraints.NotBlank;

public class OrdinateurRequest {

	@NotBlank
	private String libelle;
	@NotBlank
	private String adresseMac;
	@NotBlank
	private String dateAchat;
	private Integer salleId;
	@NotBlank
	private String status;
	@NotBlank
	private String os;
	
	public OrdinateurRequest() {	
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAdresseMac() {
		return adresseMac;
	}

	public void setAdresseMac(String adresseMac) {
		this.adresseMac = adresseMac;
	}

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Integer getSalleId() {
		return salleId;
	}

	public void setSalleId(Integer salleId) {
		this.salleId = salleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
	
	
	
}
