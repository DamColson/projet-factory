package ajc.formation.projet_factory.dto.request;

import jakarta.validation.constraints.NotBlank;

public class SalleRequest {

	@NotBlank
	private String libelle;
	@NotBlank
	private Integer superficie;
	
	public SalleRequest() {
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}
	
	
}
