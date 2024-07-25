package ajc.formation.projet_factory.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class VideoProjecteurRequest {

	@NotBlank
	private String libelle;
	@NotBlank
	private String adresseMac;
	@NotBlank
	private LocalDate dateAchat;
	private Integer salleId;
	@NotBlank
	private String status;

	public VideoProjecteurRequest() {
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

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
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

	
}
