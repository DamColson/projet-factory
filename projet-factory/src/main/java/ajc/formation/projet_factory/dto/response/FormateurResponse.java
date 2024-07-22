package ajc.formation.projet_factory.dto.response;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Formateur;


public class FormateurResponse {

	@JsonView(CustomJsonViews.Common.class)
	private String nom;
	@JsonView(CustomJsonViews.Common.class)
    private String prenom;
	@JsonView(CustomJsonViews.Common.class)
    private String telephone;
	@JsonView(CustomJsonViews.Common.class)
    private String mail;
    @JsonView(CustomJsonViews.FormateurWithAttributes.class)
    private CompteResponse compteResponse;
    @JsonView(CustomJsonViews.FormateurWithAttributes.class)
    private OrdinateurResponse ordinateurResponse;
    @JsonView(CustomJsonViews.FormateurWithAttributes.class)
    private List<BlocResponse> blocsResponse;
    @JsonView(CustomJsonViews.FormateurWithAttributes.class)
    private VideoProjecteurResponse videoProjecteurResponse;
    @JsonView(CustomJsonViews.FormateurWithAttributes.class)
    private Set<CompetenceResponse> competencesResponse;

    public FormateurResponse() {
	}
    
    public FormateurResponse(Formateur formateur) {
    	BeanUtils.copyProperties(formateur, this);
    	if(formateur.getCompte()!=null) {
    		this.compteResponse = new CompteResponse(formateur.getCompte());	
    	}
    	if(formateur.getOrdinateur()!=null) {
    		this.ordinateurResponse = new OrdinateurResponse(formateur.getOrdinateur());
    	}
    	if(formateur.getVideoProjecteur()!=null) {
    		this.videoProjecteurResponse = new VideoProjecteurResponse(formateur.getVideoProjecteur());
    	}
    	if(formateur.getCompetences()!=null) {
    		this.competencesResponse = formateur.getCompetences().stream().map(competence->{
        		return new CompetenceResponse(competence);
        	}).collect(Collectors.toSet());
    	}
    	
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public CompteResponse getCompteResponse() {
		return compteResponse;
	}

	public void setCompteResponse(CompteResponse compteResponse) {
		this.compteResponse = compteResponse;
	}

	public OrdinateurResponse getOrdinateurResponse() {
		return ordinateurResponse;
	}

	public void setOrdinateurResponse(OrdinateurResponse ordinateurResponse) {
		this.ordinateurResponse = ordinateurResponse;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<BlocResponse> getBlocsResponse() {
		return blocsResponse;
	}

	public void setBlocsResponse(List<BlocResponse> blocsResponse) {
		this.blocsResponse = blocsResponse;
	}

	public VideoProjecteurResponse getVideoProjecteurResponse() {
		return videoProjecteurResponse;
	}

	public void setVideoProjecteurResponse(VideoProjecteurResponse videoProjecteurResponse) {
		this.videoProjecteurResponse = videoProjecteurResponse;
	}

	public Set<CompetenceResponse> getCompetencesResponse() {
		return competencesResponse;
	}

	public void setCompetencesResponse(Set<CompetenceResponse> competencesResponse) {
		this.competencesResponse = competencesResponse;
	}
	
	
    
    
}
