package ajc.formation.projet_factory.dto.request;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class FormationRequest {

	private LocalDate debut;
	private LocalDate fin;
	private String prerequis;
	private Integer gestionnaireId;
	private Set<Integer> stagiairesId;
	private List<Integer> blocsId;
	
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

	public Set<Integer> getStagiairesId() {
		return stagiairesId;
	}

	public void setStagiairesId(Set<Integer> stagiairesId) {
		this.stagiairesId = stagiairesId;
	}

	public List<Integer> getBlocsId() {
		return blocsId;
	}

	public void setBlocsId(List<Integer> blocsId) {
		this.blocsId = blocsId;
	}
	
	
	
}
