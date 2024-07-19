package ajc.formation.projet_factory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import ajc.formation.projet_factory.dao.IDAOBloc;
import ajc.formation.projet_factory.dao.IDAOCompetence;
import ajc.formation.projet_factory.dao.IDAOVideoProjecteur;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Role;
import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.model.Technicien;
import ajc.formation.projet_factory.services.CompteService;
import ajc.formation.projet_factory.services.FormateurService;
import ajc.formation.projet_factory.services.OrdinateurService;
import ajc.formation.projet_factory.services.SalleService;
import ajc.formation.projet_factory.services.TechnicienService;

@SpringBootTest
public class SpringTest {

	
	@Autowired
	FormateurService formateurSrv;
	@Autowired
	IDAOBloc daoBloc;
	@Autowired
	IDAOVideoProjecteur daoVideoProjecteur;
	@Autowired
	IDAOCompetence daoCompetence;
	@Autowired
	CompteService compteSrv;
	@Autowired
	TechnicienService technicienSrv;
	@Autowired
	OrdinateurService ordinateurSrv;
	@Autowired
	SalleService salleSrv;
	
	@Test
	@Transactional()
	@Commit
	@Disabled
	public void test() {
		
	Salle salle = new Salle();	
	Ordinateur ordinateur = new Ordinateur();
	Compte compte = new Compte();
	Technicien technicien = new Technicien();
	Set<Ordinateur> ordinateurs = new HashSet<Ordinateur>();

	
	
	
	salle.setLibelle("une salle");
	salle.setSuperficie(12);
	
	ordinateur.setAdresseMac("mac");
	ordinateur.setDateAchat(LocalDate.now().toString());	
	ordinateurs.add(ordinateur);
	ordinateur.setEmplacemeht(salle);
	
	technicien.setMail("noah@gmail.com");
	technicien.setNom("Fiquet");
	technicien.setPrenom("Noah");
	technicien.setOrdinateur(ordinateur);
	technicien.setTelephone("06XXXXXXXX");

	compte.setLogin("nfiquet");
	compte.setPassword("nfiquet");
	compte.setRole(Role.ROLE_TECHNICIEN);
	
	technicien.setCompte(compte);
	
	salleSrv.insert(salle);
	ordinateurSrv.insert(ordinateur);
	compteSrv.insert(compte);
	technicienSrv.insert(technicien);
	
	
//	Ordinateur ordinateur = new Ordinateur();
//	Bloc bloc = new Bloc();	
//	Bloc bloc2 = new Bloc();	
//	VideoProjecteur videoProj = new VideoProjecteur();
//	
//	ordinateur.setAdresseMac("osef");
//	ordinateur.setDateAchat(LocalDate.now().toString());
//	ordinateur.setLibelle("un libelle");
//	ordinateur.setOs("mac");
//	
//	bloc.setCode("1234");
//	bloc.setDateDebut(LocalDate.now());
//	bloc.setDateFin(LocalDate.now());
//	bloc.setObjectif("etre le plus feurt");
//	
//	
//	bloc2.setCode("12345");
//	bloc2.setDateDebut(LocalDate.now());
//	bloc2.setDateFin(LocalDate.now());
//	bloc2.setObjectif("etre le plus feurt");
//	
//		
//	videoProj.setAdresseMac("adresse");
//	videoProj.setDateAchat(LocalDate.now().toString());
//	videoProj.setLibelle("truc");
//		
//	Formateur formateur = new Formateur();
//	
//	formateur.setNom("Fiquet");
//	formateur.setPrenom("Noah");
//	formateur.setMail("noah@noah.com");
//	formateur.setPassword("password");
//	formateur.setTelephone("06XXXXXXXX");
//
//	
//	formateur = formateurSrv.insert(formateur);
//
//	Set<Formateur> formateurs = new HashSet<Formateur>();
//	formateurs.add(formateur);
//	
//	Competence competence =  new Competence();
//	competence.setNom("comp1");
//	competence.setFormateurs(formateurs);
//	
//	Competence competence2 =  new Competence();
//	competence2.setNom("comp2");
//	competence2.setFormateurs(formateurs);
//	
//	daoCompetence.save(competence);
//	daoCompetence.save(competence2);
//	
//	bloc.setFormateur(formateur);
//	bloc2.setFormateur(formateur);
//	ordinateur.setFormateur(formateur);
//	videoProj.setFormateur(formateur);
//	
//	ordinateur = daoOrdinateur.save(ordinateur);
//	videoProj = daoVideoProjecteur.save(videoProj);
//	bloc = daoBloc.save(bloc);
//	bloc2 = daoBloc.save(bloc2);
//
//	List<Competence> competences = daoCompetence.findByFormateurs(formateur);
//	competences.stream().forEach(comp->System.out.println(comp.getFormateurs()));
//	Formateur f = formateurSrv.getById(8);
//	formateurSrv.delete(f);
	
	

		
		
	}
}
