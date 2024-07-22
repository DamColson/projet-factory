package ajc.formation.projet_factory.dto.response;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Salle;

public class SalleResponse {

	@JsonView(CustomJsonViews.Common.class)
	private String libelle;
	@JsonView(CustomJsonViews.Common.class)
	private Integer superficie;
	@JsonView(CustomJsonViews.SalleWithAttributes.class)
	private Set<OrdinateurResponse> ordinateursResponse;
	@JsonView(CustomJsonViews.SalleWithAttributes.class)
	private Set<BlocResponse> blocsResponse;
	@JsonView(CustomJsonViews.SalleWithAttributes.class)
	private VideoProjecteurResponse videoProjecteurResponse;

	public SalleResponse() {
	}

	public SalleResponse(Salle salle) {
		BeanUtils.copyProperties(salle, this);
		if(salle.getVideoProjecteurs()!=null) {
			this.videoProjecteurResponse = new VideoProjecteurResponse(salle.getVideoProjecteurs());
		}
		if(salle.getOrdinateurs()!=null) {
			this.ordinateursResponse = salle.getOrdinateurs().stream().map(ordinateur -> {
				return new OrdinateurResponse(ordinateur);
			}).collect(Collectors.toSet());
		}
		if(salle.getBlocs()!=null) {
			this.blocsResponse = salle.getBlocs().stream().map(bloc -> {
				return new BlocResponse(bloc);
			}).collect(Collectors.toSet());
		}
		
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

	public Set<OrdinateurResponse> getOrdinateursResponse() {
		return ordinateursResponse;
	}

	public void setOrdinateursResponse(Set<OrdinateurResponse> ordinateursResponse) {
		this.ordinateursResponse = ordinateursResponse;
	}

	public Set<BlocResponse> getBlocsResponse() {
		return blocsResponse;
	}

	public void setBlocsResponse(Set<BlocResponse> blocsResponse) {
		this.blocsResponse = blocsResponse;
	}

	public VideoProjecteurResponse getVideoProjecteurResponse() {
		return videoProjecteurResponse;
	}

	public void setVideoProjecteurResponse(VideoProjecteurResponse videoProjecteurResponse) {
		this.videoProjecteurResponse = videoProjecteurResponse;
	}

}
