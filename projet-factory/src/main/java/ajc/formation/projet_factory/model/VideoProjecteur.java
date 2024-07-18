package ajc.formation.projet_factory.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "videoProjecteur")
public class VideoProjecteur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String libelle;
	
	private String adresseMac;
	
	private String dateAchat;
	
	@OneToOne
	private Salle emplacement;
	
	@OneToOne
	private Formateur formateur;
	
	private String status;
	
	public VideoProjecteur() {
	}

	
	public VideoProjecteur(Integer id, String libelle, String adresseMac, String dateAchat, Salle emplacement,
			Formateur formateur, String status) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.adresseMac = adresseMac;
		this.dateAchat = dateAchat;
		this.emplacement = emplacement;
		this.formateur = formateur;
		this.status = status;
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

	public Salle getEmplacemeht() {
		return emplacement;
	}

	public void setEmplacemeht(Salle emplacemeht) {
		this.emplacement = emplacemeht;
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

	public Salle getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Salle emplacement) {
		this.emplacement = emplacement;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoProjecteur other = (VideoProjecteur) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
