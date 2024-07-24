package ajc.formation.projet_factory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import ajc.formation.projet_factory.dao.IDAOCompte;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Role;

@SpringBootTest
class AdminInit {
	@Autowired
	IDAOCompte daoCompte;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	@Transactional
	@Commit
	void initAdmin() {
		Compte admin=new Compte();
		admin.setLogin("admin");
		admin.setRole(Role.ROLE_ADMIN);
		admin.setPassword( passwordEncoder.encode("admin"));
		daoCompte.save(admin);
	}
	
	@Test
	@Transactional
	@Commit
	void initTechnicien() {
		Compte tech=new Compte();
		tech.setLogin("technicien");
		tech.setRole(Role.ROLE_TECHNICIEN);
		tech.setPassword( passwordEncoder.encode("technicien"));
		daoCompte.save(tech);
	}
	
	@Test
	@Transactional
	@Commit
	void initGestionnaire() {
		Compte gest=new Compte();
		gest.setLogin("gestionnaire");
		gest.setRole(Role.ROLE_GESTIONNAIRE);
		gest.setPassword( passwordEncoder.encode("gestionnaire"));
		daoCompte.save(gest);
	}
	
	@Test
	@Transactional
	@Commit
	void initFormateur() {
		Compte formateur =new Compte();
		formateur.setLogin("formateur");
		formateur.setRole(Role.ROLE_FORMATEUR);
		formateur.setPassword( passwordEncoder.encode("formateur"));
		daoCompte.save(formateur);
	}
	
	@Test
	@Transactional
	@Commit
	void initStagiaire() {
		Compte stagiaire=new Compte();
		stagiaire.setLogin("stagiaire");
		stagiaire.setRole(Role.ROLE_STAGIAIRE);
		stagiaire.setPassword( passwordEncoder.encode("stagiaire"));
		daoCompte.save(stagiaire);
	}
	
	@Test
	@Transactional
	@Commit
	@Disabled
	void initUser() {
		Compte user=daoCompte.findById(2).get();
		user.setPassword( passwordEncoder.encode("technicien"));
		daoCompte.save(user);
	}
}
