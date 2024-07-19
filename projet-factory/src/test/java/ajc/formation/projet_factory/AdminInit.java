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

@SpringBootTest
class AdminInit {
	@Autowired
	IDAOCompte daoCompte;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	@Transactional
	@Commit
	@Disabled
	void initAdmin() {
		Compte admin=daoCompte.findById(1).get();
		admin.setPassword( passwordEncoder.encode("admin"));
		daoCompte.save(admin);
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
