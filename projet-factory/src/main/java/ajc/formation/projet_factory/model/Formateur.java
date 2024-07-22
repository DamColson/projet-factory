package ajc.formation.projet_factory.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "formateur")
public class Formateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    
    @OneToOne
    private Compte compte;
    
    @OneToOne
    private Ordinateur ordinateur;
    
    @OneToMany(mappedBy="formateur")
    private List<Bloc> blocs;
    
    @OneToOne
    @Column(name = "video_projecteur_id")
    private VideoProjecteur videoProjecteur;
    
    @ManyToMany(mappedBy = "formateurs")
    private Set<Competence> competences;

    public Formateur(){}
    
    

    public Formateur(Integer id, String nom, String prenom, String telephone, String mail, Compte compte,
			Ordinateur ordinateur, List<Bloc> blocs, VideoProjecteur videoProjecteur, Set<Competence> competences) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.compte = compte;
		this.ordinateur = ordinateur;
		this.blocs = blocs;
		this.videoProjecteur = videoProjecteur;
		this.competences = competences;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Compte getCompte() {
		return compte;
	}
    
	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Ordinateur getOrdinateur() {
        return ordinateur;
    }

    public void setOrdinateur(Ordinateur ordinateur) {
        this.ordinateur = ordinateur;
    }

    public List<Bloc> getBlocs() {
        return blocs;
    }

    public void setBlocs(List<Bloc> blocs) {
        this.blocs = blocs;
    }

    public VideoProjecteur getVideoProjecteur() {
        return videoProjecteur;
    }

    public void setVideoProjecteur(VideoProjecteur videoProjecteur) {
        this.videoProjecteur = videoProjecteur;
    }

    public Set<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
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
		Formateur other = (Formateur) obj;
		return Objects.equals(id, other.id);
	}

    

}
