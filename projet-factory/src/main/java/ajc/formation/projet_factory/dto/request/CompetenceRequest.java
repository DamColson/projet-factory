package ajc.formation.projet_factory.dto.request;


import jakarta.validation.constraints.NotBlank;

public class CompetenceRequest {

	@NotBlank
	private String nom;
	
	public CompetenceRequest() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "CompetenceRequest [nom=" + nom + "]";
	}
	
	
	
}
