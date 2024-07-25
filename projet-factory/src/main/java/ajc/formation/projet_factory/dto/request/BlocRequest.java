package ajc.formation.projet_factory.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class BlocRequest {

	@NotNull
	private String libelle;
	@NotNull
	@Future
	private LocalDate dateDebut;
	@NotNull
	@Future
	private LocalDate dateFin;
	@NotBlank
	private String code;
	@NotBlank
	private String objectif;	
	private Integer formateurId;
	@NotNull
	private Integer matiereId;
	private Integer salleId;
	
	public Integer getSalleId() {
		return salleId;
	}

	public void setSalleId(Integer salleId) {
		this.salleId = salleId;
	}

	public BlocRequest() {
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

	public Integer getFormateurId() {
		return formateurId;
	}

	public void setFormateurId(Integer formateurId) {
		this.formateurId = formateurId;
	}

	public Integer getMatiereId() {
		return matiereId;
	}

	public void setMatiereId(Integer matiereId) {
		this.matiereId = matiereId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
	
}
