package ajc.formation.projet_factory.dto.response;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Bloc;


public class BlocResponse {

	@JsonView(CustomJsonViews.Common.class)
	private LocalDate dateDebut;
	@JsonView(CustomJsonViews.Common.class)
	private LocalDate dateFin;
	@JsonView(CustomJsonViews.Common.class)
	private String code;
	@JsonView(CustomJsonViews.Common.class)
	private String objectif;
	
	@JsonView(CustomJsonViews.BlocWithAttributes.class)
	private FormateurResponse formateurResponse;
	@JsonView(CustomJsonViews.BlocWithAttributes.class)
	private MatiereResponse matiereResponse;
	@JsonView(CustomJsonViews.BlocWithAttributes.class)
	private SalleResponse salleResponse;
	@JsonView(CustomJsonViews.BlocWithAttributes.class)
	private Set<FormationResponse> formationsResponse;
	
	public BlocResponse() {
	}
	
	public BlocResponse(Bloc bloc) {
		BeanUtils.copyProperties(bloc, this);
		this.formateurResponse = new FormateurResponse(bloc.getFormateur());
		this.matiereResponse = new MatiereResponse(bloc.getMatiere());
		this.salleResponse = new SalleResponse(bloc.getSalle());
		this.formationsResponse = bloc.getFormations().stream().map(formation->{
			return new FormationResponse(formation);
		}).collect(Collectors.toSet());
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public FormateurResponse getFormateurResponse() {
		return formateurResponse;
	}

	public void setFormateurResponse(FormateurResponse formateurResponse) {
		this.formateurResponse = formateurResponse;
	}

	public MatiereResponse getMatiereResponse() {
		return matiereResponse;
	}

	public void setMatiereResponse(MatiereResponse matiereResponse) {
		this.matiereResponse = matiereResponse;
	}

	public SalleResponse getSalleResponse() {
		return salleResponse;
	}

	public void setSalleResponse(SalleResponse salleResponse) {
		this.salleResponse = salleResponse;
	}

	public Set<FormationResponse> getFormationsResponse() {
		return formationsResponse;
	}

	public void setFormationsResponse(Set<FormationResponse> formationsResponse) {
		this.formationsResponse = formationsResponse;
	}
	
	
}