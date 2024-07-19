package ajc.formation.projet_factory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOCompte;
import ajc.formation.projet_factory.exceptions.CompteException;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Role;


@Service
public class CompteService implements UserDetailsService{

	@Autowired
	IDAOCompte daoCompte;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return daoCompte.findByLogin(username).orElseThrow(()->{
			throw new UsernameNotFoundException("Utilisateur inconnu");
			});
	}
	
	public Compte create(Compte compte) {
		if(compte.getLogin()==null||compte.getLogin().isBlank()||daoCompte.findByLogin(compte.getLogin()).isPresent()) {
			throw new CompteException("probleme login");
		}
		
		if(compte.getPassword()==null||compte.getPassword().isBlank()) {
			throw new CompteException("probleme password");
		}
		
		compte.setPassword(passwordEncoder.encode(compte.getPassword()));
		compte.setRole(Role.ROLE_USER);
		
		return daoCompte.save(compte);
	}
}
