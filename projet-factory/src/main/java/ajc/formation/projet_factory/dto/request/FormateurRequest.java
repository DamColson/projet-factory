package ajc.formation.projet_factory.dto.request;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class FormateurRequest {

	@NotBlank
	private String nom;
	@NotBlank
    private String prenom;
	@NotBlank
    private String telephone;
	@NotBlank
    private String mail;
    @NotNull
    private Integer compteId;
    private Integer ordinateurId;
    private Integer videoProjecteurId;
    private Set<Integer> competencesId;
    private List<Integer> blocsId;
    
    public FormateurRequest() {
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

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getCompteId() {
		return compteId;
	}

	public void setCompteId(Integer compteId) {
		this.compteId = compteId;
	}

	public Integer getOrdinateurId() {
		return ordinateurId;
	}

	public void setOrdinateurId(Integer ordinateurId) {
		this.ordinateurId = ordinateurId;
	}

	public Integer getVideoProjecteurId() {
		return videoProjecteurId;
	}

	public void setVideoProjecteurId(Integer videoProjecteurId) {
		this.videoProjecteurId = videoProjecteurId;
	}

	public Set<Integer> getCompetencesId() {
		return competencesId;
	}

	public void setCompetencesId(Set<Integer> competencesId) {
		this.competencesId = competencesId;
	}

	public List<Integer> getBlocsId() {
		return blocsId;
	}

	public void setBlocsId(List<Integer> blocsId) {
		this.blocsId = blocsId;
	}

    
}
