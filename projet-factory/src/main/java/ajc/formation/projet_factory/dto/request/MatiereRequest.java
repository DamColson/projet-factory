package ajc.formation.projet_factory.dto.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

public class MatiereRequest {

	@NotBlank
	private String titre;
	private String contenu;
	private Set<Integer> competencesId;
	
	public MatiereRequest() {
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public Set<Integer> getCompetencesId() {
		return competencesId;
	}

	public void setCompetencesId(Set<Integer> competencesId) {
		this.competencesId = competencesId;
	}
	
	
	
}
