package ajc.formation.projet_factory.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salle")
public class Salle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String libelle;
	
	private Integer superficie;
	
	@OneToMany(mappedBy="emplacement")
	private Set<Ordinateur> ordinateurs;
	
	@OneToMany(mappedBy="salle")
	private Set<Bloc> blocs;
	
	@OneToOne(mappedBy="emplacement")
	private VideoProjecteur videoProjecteurs;
	
	public Salle() {
	}

	public Salle(Integer id, String libelle, Integer superficie, Set<Ordinateur> ordinateurs, Set<Bloc> blocs,
			VideoProjecteur videoProjecteurs) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.superficie = superficie;
		this.ordinateurs = ordinateurs;
		this.blocs = blocs;
		this.videoProjecteurs = videoProjecteurs;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}

	public Set<Ordinateur> getOrdinateurs() {
		return ordinateurs;
	}

	public void setOrdinateurs(Set<Ordinateur> ordinateurs) {
		this.ordinateurs = ordinateurs;
	}

	public VideoProjecteur getVideoProjecteurs() {
		return videoProjecteurs;
	}

	public void setVideoProjecteurs(VideoProjecteur videoProjecteurs) {
		this.videoProjecteurs = videoProjecteurs;
	}

	public Set<Bloc> getBlocs() {
		return blocs;
	}

	public void setBlocs(Set<Bloc> blocs) {
		this.blocs = blocs;
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
		Salle other = (Salle) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
