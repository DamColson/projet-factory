package ajc.formation.projet_factory.dto.response;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Matiere;


public class MatiereResponse {

	@JsonView(CustomJsonViews.Common.class)
	private String titre;
	@JsonView(CustomJsonViews.Common.class)
	private String contenu;
	@JsonView(CustomJsonViews.MatiereWithAttributes.class)
	private Set<CompetenceResponse> competencesResponse;
	@JsonView(CustomJsonViews.MatiereWithAttributes.class)
	private Set<BlocResponse> blocsResponse;
	
	public MatiereResponse() {
	}
	
	public MatiereResponse(Matiere matiere) {
		BeanUtils.copyProperties(matiere, this);
		if(matiere.getBlocs()!=null) {
			this.blocsResponse = matiere.getBlocs().stream().map(bloc->{
				return new BlocResponse(bloc);
			}).collect(Collectors.toSet());
		}
		if(matiere.getBlocs()!=null) {
			this.competencesResponse = matiere.getCompetences().stream().map(competence->{
				return new CompetenceResponse(competence);
			}).collect(Collectors.toSet());
		}
		
		
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

	public Set<CompetenceResponse> getCompetencesResponse() {
		return competencesResponse;
	}

	public void setCompetencesResponse(Set<CompetenceResponse> competencesResponse) {
		this.competencesResponse = competencesResponse;
	}

	public Set<BlocResponse> getBlocsResponse() {
		return blocsResponse;
	}

	public void setBlocsResponse(Set<BlocResponse> blocsResponse) {
		this.blocsResponse = blocsResponse;
	}
	
	
}
