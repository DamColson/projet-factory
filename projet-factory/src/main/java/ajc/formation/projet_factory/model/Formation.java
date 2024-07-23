package ajc.formation.projet_factory.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "formation")
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "debutFormation")
	private LocalDate debut;
	@Column(name = "finFormation")
	private LocalDate fin;
	private String prerequis;
	
	
	@OneToMany(mappedBy="formation")
	private Set<Stagiaire> stagiaires;
	
	@ManyToMany
	@JoinTable(
	name = "bloc_formation",
	joinColumns = @JoinColumn(name = "formation_id"),
	inverseJoinColumns = @JoinColumn(name = "bloc_id")
	)
	private Set<Bloc> blocs;
	
	@ManyToOne
	private Gestionnaire gestionnaire;
	
	public Formation() {
		
	}

	public Formation(Integer id, LocalDate debut, LocalDate fin, String prerequis, Set<Stagiaire> stagiaires,
			Set<Bloc> blocs, Gestionnaire gestionnaire) {
		this.id = id;
		this.debut = debut;
		this.fin = fin;
		this.prerequis = prerequis;
		this.stagiaires = stagiaires;
		this.blocs = blocs;
		this.gestionnaire = gestionnaire;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public String getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}

	public Set<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(Set<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Set<Bloc> getBlocs() {
		return blocs;
	}

	public void setBlocs(Set<Bloc> blocs) {
		this.blocs = blocs;
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
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
		Formation other = (Formation) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
