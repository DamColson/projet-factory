package ajc.formation.projet_factory.dto.response;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.Gestionnaire;
import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.model.Stagiaire;
import ajc.formation.projet_factory.model.Technicien;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class OrdinateurResponse {
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String libelle;
	@JsonView(CustomJsonViews.Common.class)
	private String adresseMac;
	@JsonView(CustomJsonViews.Common.class)
	private String dateAchat;
	
	private Salle emplacement;

	@JsonView(CustomJsonViews.Common.class)
	private String status;

	private Formateur formateur;

	private Stagiaire stagiaire;

	private Gestionnaire gestionnaire;

	@JsonView(CustomJsonViews.OrdinateurWithTechnicien.class)
	private Technicien technicien;
	
	@JsonView(CustomJsonViews.Common.class)
	private String os;

}
