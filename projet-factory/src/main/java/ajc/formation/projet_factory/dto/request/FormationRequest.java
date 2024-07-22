package ajc.formation.projet_factory.dto.request;

import java.time.LocalDate;

public class FormationRequest {

	private LocalDate debut;
	private LocalDate fin;
	private String prerequis;
	private Integer gestionnaireId;
	
	public FormationRequest() {
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public String getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}

	public Integer getGestionnaireId() {
		return gestionnaireId;
	}

	public void setGestionnaireId(Integer gestionnaireId) {
		this.gestionnaireId = gestionnaireId;
	}
	
	
}
