package ajc.formation.projet_factory.dto.response;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Competence;

public class CompetenceResponse {

	@JsonView(CustomJsonViews.Common.class)
	private String nom;
	@JsonView(CustomJsonViews.CompetenceWithAttributes.class)
	private Set<MatiereResponse> matieresResponse;
	@JsonView(CustomJsonViews.CompetenceWithAttributes.class)
	private Set<FormateurResponse> formateursResponse;
	
	public CompetenceResponse() {
	}
	
	public CompetenceResponse(Competence competence) {
		BeanUtils.copyProperties(competence, this);
		this.matieresResponse = competence.getMatieres().stream().map(matiere->{
			return new MatiereResponse(matiere);
		}).collect(Collectors.toSet());
		this.formateursResponse = competence.getFormateurs().stream().map(formateur->{
			return new FormateurResponse(formateur);
		}).collect(Collectors.toSet());
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<MatiereResponse> getMatieresResponse() {
		return matieresResponse;
	}

	public void setMatieresResponse(Set<MatiereResponse> matieresResponse) {
		this.matieresResponse = matieresResponse;
	}

	public Set<FormateurResponse> getFormateursResponse() {
		return formateursResponse;
	}

	public void setFormateursResponse(Set<FormateurResponse> formateursResponse) {
		this.formateursResponse = formateursResponse;
	}
	
	
}