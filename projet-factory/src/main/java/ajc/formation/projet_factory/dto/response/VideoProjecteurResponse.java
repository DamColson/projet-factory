package ajc.formation.projet_factory.dto.response;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.VideoProjecteur;

public class VideoProjecteurResponse {
	
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String libelle;
	@JsonView(CustomJsonViews.Common.class)	
	private String adresseMac;
	@JsonView(CustomJsonViews.Common.class)
	private LocalDate dateAchat;
	@JsonView(CustomJsonViews.VideoProjecteurWithAttributes.class)
	private SalleResponse salleResponse;
	@JsonView(CustomJsonViews.VideoProjecteurWithAttributes.class)
	private FormateurResponse formateurResponse;
	@JsonView(CustomJsonViews.Common.class)
	private String status;
	
	public VideoProjecteurResponse() {
	}
	
	public VideoProjecteurResponse(VideoProjecteur videoProjecteur,boolean bool) {
		BeanUtils.copyProperties(videoProjecteur, this,"status");
		this.status = videoProjecteur.getStatus().toString();
		if(bool) {
			if(videoProjecteur.getSalle()!=null) {
				this.salleResponse = new SalleResponse(videoProjecteur.getSalle(),false);
			}
			if(videoProjecteur.getFormateur()!=null) {
				this.formateurResponse = new FormateurResponse(videoProjecteur.getFormateur(),false);
			}
		}
	}
	
	public VideoProjecteurResponse(VideoProjecteur videoProjecteur) {
		this(videoProjecteur,true);
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAdresseMac() {
		return adresseMac;
	}

	public void setAdresseMac(String adresseMac) {
		this.adresseMac = adresseMac;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}

	public SalleResponse getSalleResponse() {
		return salleResponse;
	}

	public void setSalleResponse(SalleResponse salleResponse) {
		this.salleResponse = salleResponse;
	}

	public FormateurResponse getFormateurResponse() {
		return formateurResponse;
	}

	public void setFormateurResponse(FormateurResponse formateurResponse) {
		this.formateurResponse = formateurResponse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
