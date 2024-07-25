package ajc.formation.projet_factory.dto.request;

import java.time.LocalDate;
import java.util.Set;

public class FormationRequest {

	private String libelle;
	private LocalDate debut;
	private LocalDate fin;
	private String prerequis;
	private Integer gestionnaireId;
	private Set<Integer> stagiairesId;
	private Set<Integer> blocsId;
	
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

	public Set<Integer> getBlocsId() {
		return blocsId;
	}

	public void setBlocsId(Set<Integer> blocsId) {
		this.blocsId = blocsId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
	
}
