package ajc.formation.projet_factory;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.Gestionnaire;
import ajc.formation.projet_factory.model.Matiere;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Role;
import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.model.Stagiaire;
import ajc.formation.projet_factory.model.Status;
import ajc.formation.projet_factory.model.Technicien;
import ajc.formation.projet_factory.model.VideoProjecteur;
import ajc.formation.projet_factory.services.CompetenceService;
import ajc.formation.projet_factory.services.CompteService;
import ajc.formation.projet_factory.services.FormateurService;
import ajc.formation.projet_factory.services.GestionnaireService;
import ajc.formation.projet_factory.services.MatiereService;
import ajc.formation.projet_factory.services.OrdinateurService;
import ajc.formation.projet_factory.services.SalleService;
import ajc.formation.projet_factory.services.StagiaireService;
import ajc.formation.projet_factory.services.TechnicienService;
import ajc.formation.projet_factory.services.VideoProjecteurService;

@SpringBootTest
class AdminInit {
	@Autowired
	CompteService compteSrv;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	CompetenceService competenceSrv;
	@Autowired
	SalleService salleSrv;
	@Autowired
	MatiereService matiereSrv;
	@Autowired
	OrdinateurService ordinateurSrv;
	@Autowired
	VideoProjecteurService videoProjecteurSrv;
	@Autowired
	TechnicienService technicienSrv;
	@Autowired
	GestionnaireService gestionnaireSrv;
	@Autowired
	FormateurService formateurSrv;
	@Autowired
	StagiaireService stagiaireSrv;
	
	// ATTENTION JEUNE MARGOULIN, LES TESTS QUI SUIVENT SONT A REALISER DANS UN ORDRE SPECIFIQUE, RETIRE DONC LES @Disabled REQUIS POUR LA PREMIERE SEQUENCE DE TEST, EFFECTUE LES TESTS, REATTRIBUE LES @Disabled
	// SUR LA PREMIERE SEQUENCE DE TESTS AINSI EFFECTUES ET RETIRE LES @Disabled DE LA SECONDE SEQUENCE DE TESTS. UNE FOIS CELA FAIT, REATTRIBUE LES @Disabled SUR LA SECONDE SEQUENCE DE TEST ET TU SERAS PRET 
	// A AFFRONTER LE MONDE !!
	
	// ICI COMMENCE LA PREMIERE SEQUENCE DE TESTS !
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void initCompteAdmin() {
		Compte admin = new Compte();
		admin.setLogin("admin");
		admin.setRole(Role.ROLE_ADMIN);
		admin.setPassword(passwordEncoder.encode("admin"));
		compteSrv.insert(admin);
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void initCompteTechnicien() {
		for(int i=0;i<3;i++) {
			Compte tech = new Compte();
			tech.setLogin("technicien"+i);
			tech.setRole(Role.ROLE_TECHNICIEN);
			tech.setPassword(passwordEncoder.encode("technicien"+i));
			compteSrv.insert(tech);
		}
		
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void initCompteGestionnaire() {
		for(int i =0;i<3;i++) {
			Compte gest = new Compte();
			gest.setLogin("gestionnaire"+i);
			gest.setRole(Role.ROLE_GESTIONNAIRE);
			gest.setPassword(passwordEncoder.encode("gestionnaire"+i));
			compteSrv.insert(gest);
		}	
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void initCompteFormateur() {
		for(int i=0;i<3;i++) {
			Compte formateur = new Compte();
			formateur.setLogin("formateur"+i);
			formateur.setRole(Role.ROLE_FORMATEUR);
			formateur.setPassword(passwordEncoder.encode("formateur"+i));
			compteSrv.insert(formateur);
		}		
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void initCompteStagiaire() {
		for(int i=0;i<3;i++) {
			Compte stagiaire = new Compte();
			stagiaire.setLogin("stagiaire"+i);
			stagiaire.setRole(Role.ROLE_STAGIAIRE);
			stagiaire.setPassword(passwordEncoder.encode("stagiaire"+i));
			compteSrv.insert(stagiaire);
		}
		
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void initCompetence() {
		for (int i = 0; i < 20; i++) {
			Competence competence = new Competence();
			competence.setNom("competence" + i);
			competenceSrv.insert(competence);
		}
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void initSalle() {
		for (int i = 0; i < 3; i++) {
			Salle salle = new Salle();
			salle.setLibelle("salle" + i);
			salle.setSuperficie(100 + i);
			salleSrv.insert(salle);
		}
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void initMatiere() {
		for (int i = 0; i < 3; i++) {
			Matiere matiere = new Matiere();
			matiere.setTitre("matiere" + i);
			matiere.setContenu("contenu" + i);
			matiereSrv.insert(matiere);
		}
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void initOrdinateur() {
		Salle salle = salleSrv.getById(1);
		for (int i = 0; i < 20; i++) {
			Ordinateur ordinateur = new Ordinateur();
			ordinateur.setLibelle("ordinateur" + i);
			ordinateur.setAdresseMac("adresseMac" + i);
			ordinateur.setDateAchat("2022-02-28");
			ordinateur.setOs("Windows");
			ordinateur.setStatus(Status.DISPONIBLE);
			ordinateur.setSalle(salle);
			ordinateurSrv.insert(ordinateur);
		}
	}
	// ICI SE TERMINE LA PREMIERE SEQUENCE DE TESTS !
	
	// ICI COMMENCE LA SECONDE SEQUENCE DE TESTS !
	@Test
	@Transactional
	@Commit
	void initVideoProjecteur() {
		for (int i = 0; i < 3; i++) {
			int index = i+1;
			Salle salle = salleSrv.getById(index);
			VideoProjecteur videoProjecteur = new VideoProjecteur();
			videoProjecteur.setLibelle("videoProjecteur"+i);
			videoProjecteur.setAdresseMac("adresseMac"+i);
			videoProjecteur.setDateAchat("2022-02-27");
			videoProjecteur.setStatus(Status.INDISPONIBLE);
			videoProjecteur.setSalle(salle);
			videoProjecteurSrv.insert(videoProjecteur);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void initTechnicien() {
		List<Compte> comptes = compteSrv.getByRole(Role.ROLE_TECHNICIEN);
		List<Ordinateur> ordinateurs = ordinateurSrv.getAllDisponible();
		for(int i=0;i<comptes.size();i++) {
			Compte compte = comptes.get(i);
			Technicien technicien = new Technicien();
			technicien.setCompte(compte);
			technicien.setNom("nom"+i);
			technicien.setPrenom("prenom"+i);
			technicien.setTelephone("06XXXXXXXX");
			technicien.setMail("technicien"+i+"@gmail.com");
			technicien.setOrdinateur(ordinateurs.get(i));
			technicienSrv.insert(technicien);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void initGestionnaire() {
		List<Compte> comptes = compteSrv.getByRole(Role.ROLE_GESTIONNAIRE);
		List<Ordinateur> ordinateurs = ordinateurSrv.getAllDisponible();
		for(int i=0;i<comptes.size();i++) {
			Compte compte = comptes.get(i);
			Gestionnaire gestionnaire = new Gestionnaire();
			gestionnaire.setCompte(compte);
			gestionnaire.setNom("nom"+i);
			gestionnaire.setPrenom("prenom"+i);
			gestionnaire.setTelephone("06XXXXXXXX");
			gestionnaire.setMail("technicien"+i+"@gmail.com");
			gestionnaire.setOrdinateur(ordinateurs.get(i));
			gestionnaireSrv.insert(gestionnaire);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void initFormateur() {
		List<Compte> comptes = compteSrv.getByRole(Role.ROLE_FORMATEUR);
		List<Ordinateur> ordinateurs = ordinateurSrv.getAllDisponible();
		for(int i=0;i<comptes.size();i++) {
			Compte compte = comptes.get(i);
			Formateur formateur = new Formateur();
			formateur.setCompte(compte);
			formateur.setNom("nom"+i);
			formateur.setPrenom("prenom"+i);
			formateur.setTelephone("06XXXXXXXX");
			formateur.setMail("technicien"+i+"@gmail.com");
			formateur.setOrdinateur(ordinateurs.get(i));
			formateurSrv.insert(formateur);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void initStagiaire() {	List<Compte> comptes = compteSrv.getByRole(Role.ROLE_STAGIAIRE);
		List<Ordinateur> ordinateurs = ordinateurSrv.getAllDisponible();
		for(int i=0;i<comptes.size();i++) {
			Compte compte = comptes.get(i);
			Stagiaire stagiaire = new Stagiaire();
			stagiaire.setCompte(compte);
			stagiaire.setNom("nom"+i);
			stagiaire.setPrenom("prenom"+i);
			stagiaire.setTelephone("06XXXXXXXX");
			stagiaire.setMail("technicien"+i+"@gmail.com");
			stagiaire.setOrdinateur(ordinateurs.get(i));
			stagiaireSrv.insert(stagiaire);
		}	
	}
	
	// ICI SE TERMINE LA SECONDE SEQUENCE DE TESTS !
	// SI TU ES ARRIVE ICI, ALORS FELICITATIONS, TA QUETE EST ARRIVEE A SON TERME !
	
}
