package ajc.formation.projet_factory.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;
import ajc.formation.projet_factory.model.Ordinateur;


public class OrdinateurResponse {
	
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String libelle;
	@JsonView(CustomJsonViews.Common.class)
	private String adresseMac;
	@JsonView(CustomJsonViews.Common.class)
	private String dateAchat;
	@JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
	private SalleResponse salleResponse;
	@JsonView(CustomJsonViews.Common.class)
	private String status;
	@JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
	private FormateurResponse formateurResponse;
	@JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
	private StagiaireResponse stagiaireResponse;
	@JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
	private GestionnaireResponse gestionnaireResponse;
	@JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
	private TechnicienResponse technicienResponse;
	@JsonView(CustomJsonViews.Common.class)
	private String os;

	public OrdinateurResponse() {
	}
	
	public OrdinateurResponse(Ordinateur ordinateur,boolean bool) {
		BeanUtils.copyProperties(ordinateur, this);
		if(bool) {
			if(ordinateur.getSalle()!=null) {
				this.salleResponse = new SalleResponse(ordinateur.getSalle(),false);
			}
			if(ordinateur.getFormateur()!=null) {
				this.formateurResponse = new FormateurResponse(ordinateur.getFormateur(),false);
			}
			if(ordinateur.getStagiaire()!=null) {
				this.stagiaireResponse = new StagiaireResponse(ordinateur.getStagiaire(),false);
			}
			if(ordinateur.getGestionnaire()!=null) {
				this.gestionnaireResponse = new GestionnaireResponse(ordinateur.getGestionnaire(),false);
			}
			if(ordinateur.getTechnicien()!=null) {
				this.technicienResponse = new TechnicienResponse(ordinateur.getTechnicien(),false);
			}
		}	
	}
	
	public OrdinateurResponse(Ordinateur ordinateur) {
		this(ordinateur,true);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public FormateurResponse getFormateurResponse() {
		return formateurResponse;
	}

	public void setFormateurResponse(FormateurResponse formateurResponse) {
		this.formateurResponse = formateurResponse;
	}

	public StagiaireResponse getStagiaireResponse() {
		return stagiaireResponse;
	}

	public void setStagiaireResponse(StagiaireResponse stagiaireResponse) {
		this.stagiaireResponse = stagiaireResponse;
	}

	public GestionnaireResponse getGestionnaireResponse() {
		return gestionnaireResponse;
	}

	public void setGestionnaireResponse(GestionnaireResponse gestionnaireResponse) {
		this.gestionnaireResponse = gestionnaireResponse;
	}

	public TechnicienResponse getTechnicienResponse() {
		return technicienResponse;
	}

	public void setTechnicienResponse(TechnicienResponse technicienResponse) {
		this.technicienResponse = technicienResponse;
	}

	public SalleResponse getSalleResponse() {
		return salleResponse;
	}

	public void setSalleResponse(SalleResponse salleResponse) {
		this.salleResponse = salleResponse;
	}
	
	
}
