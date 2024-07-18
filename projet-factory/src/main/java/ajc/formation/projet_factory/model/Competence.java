package ajc.formation.projet_factory.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "competence")
public class Competence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	
	@ManyToMany
	@JoinTable(
	name = "comp_matiere",
	joinColumns = @JoinColumn(name = "competence_id"),
	inverseJoinColumns = @JoinColumn(name = "matiere_id")
	)
	private Set<Matiere> matieres;
	
	@ManyToMany
	@JoinTable(
	name = "comp_formateur",
	joinColumns = @JoinColumn(name = "competence_id"),
	inverseJoinColumns = @JoinColumn(name = "formateur_id")
	)
	private Set<Formateur> formateurs;
	
	public Competence() {
		
	}
	
	public Competence(Integer id, String nom, Set<Matiere> matieres, Set<Formateur> formateurs) {
		this.id = id;
		this.nom = nom;
		this.matieres = matieres;
		this.formateurs = formateurs;
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

	public Set<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(Set<Matiere> matieres) {
		this.matieres = matieres;
	}

	public Set<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(Set<Formateur> formateurs) {
		this.formateurs = formateurs;
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
		Competence other = (Competence) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
