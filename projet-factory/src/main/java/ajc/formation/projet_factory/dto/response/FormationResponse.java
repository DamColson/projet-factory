package ajc.formation.projet_factory.dto.response;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Formation;

public class FormationResponse {

	@JsonView(CustomJsonViews.Common.class)
	private LocalDate debut;
	@JsonView(CustomJsonViews.Common.class)
	private LocalDate fin;
	@JsonView(CustomJsonViews.Common.class)
	private String prerequis;
	@JsonView(CustomJsonViews.FormationWithAttributes.class)
	private Set<StagiaireResponse> stagiairesResponse;
	@JsonView(CustomJsonViews.FormationWithAttributes.class)
	private Set<BlocResponse> blocsResponse;
	@JsonView(CustomJsonViews.FormationWithAttributes.class)
	private GestionnaireResponse gestionnaireResponse;
	
	public FormationResponse() {
	}
	
	public FormationResponse(Formation formation,boolean bool) {
		BeanUtils.copyProperties(formation, this);
		if(bool) {
			if(formation.getGestionnaire()!=null) {
				this.gestionnaireResponse = new GestionnaireResponse(formation.getGestionnaire());
			}
			if(formation.getBlocs()!=null) {
				this.blocsResponse = formation.getBlocs().stream().map(bloc->{
					return new BlocResponse(bloc);
				}).collect(Collectors.toSet());
			}
			if(formation.getStagiaires()!=null) {
				this.stagiairesResponse = formation.getStagiaires().stream().map(stagiaire->{
					return new StagiaireResponse(stagiaire,false);
				}).collect(Collectors.toSet());
			}
		}	
	}
	
	public FormationResponse(Formation formation) {
		this(formation,true);
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

	public Set<StagiaireResponse> getStagiairesResponse() {
		return stagiairesResponse;
	}

	public void setStagiairesResponse(Set<StagiaireResponse> stagiairesResponse) {
		this.stagiairesResponse = stagiairesResponse;
	}

	public Set<BlocResponse> getBlocsResponse() {
		return blocsResponse;
	}

	public void setBlocsResponse(Set<BlocResponse> blocsResponse) {
		this.blocsResponse = blocsResponse;
	}

	public GestionnaireResponse getGestionnaireResponse() {
		return gestionnaireResponse;
	}

	public void setGestionnaireResponse(GestionnaireResponse gestionnaireResponse) {
		this.gestionnaireResponse = gestionnaireResponse;
	}
	
	
}
