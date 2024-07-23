package ajc.formation.projet_factory.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bloc")
public class Bloc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="date_debut")
	private LocalDate dateDebut;
	@Column(name="date_fin")
	private LocalDate dateFin;
	private String code;
	private String objectif;
	
	@ManyToOne
	private Formateur formateur;
	
	@ManyToOne
	private Matiere matiere;
	
	@ManyToOne
	private Salle salle;
	
	@ManyToMany(mappedBy = "blocs")
	private Set<Formation> formations;

	public Bloc() {

	}

	public Bloc(Integer id, LocalDate dateDebut, LocalDate dateFin, String code, String objectif, Formateur formateur,
			Matiere matiere, Salle salle, Set<Formation> formations) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.code = code;
		this.objectif = objectif;
		this.formateur = formateur;
		this.matiere = matiere;
		this.salle = salle;
		this.formations = formations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Set<Formation> getFormations() {
		return formations;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
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
		Bloc other = (Bloc) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
