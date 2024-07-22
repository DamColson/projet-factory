package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOCompte;
import ajc.formation.projet_factory.dao.IDAOFormateur;
import ajc.formation.projet_factory.dao.IDAOGestionnaire;
import ajc.formation.projet_factory.dao.IDAOStagiaire;
import ajc.formation.projet_factory.dao.IDAOTechnicien;
import ajc.formation.projet_factory.exceptions.CompteException;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Role;


@Service
public class CompteService implements UserDetailsService{

	@Autowired
	IDAOCompte daoCompte;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	IDAOTechnicien daoTechnicien;
	@Autowired
	IDAOFormateur daoFormateur;
	@Autowired
	IDAOGestionnaire daoGestionnaire;
	@Autowired
	IDAOStagiaire daoStagiaire;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return daoCompte.findByLogin(username).orElseThrow(()->{
			throw new UsernameNotFoundException("Utilisateur inconnu");
			});
	}
	
	public List<Compte> getAll(){
		return daoCompte.findAll();
	}
	
	public Compte insert(Compte compte) {
		if(compte.getLogin()==null||compte.getLogin().isBlank()||daoCompte.findByLogin(compte.getLogin()).isPresent()) {
			throw new CompteException("probleme login");
		}
		
		if(compte.getPassword()==null||compte.getPassword().isBlank()) {
			throw new CompteException("probleme password");
		}
		
		if(compte.getRole()==null){
			throw new CompteException("probleme compte");
		}
		
		compte.setPassword(passwordEncoder.encode(compte.getPassword()));
		
		return daoCompte.save(compte);
	}
	
	public Compte getById(Integer id) {
		if(id==null) {
			throw new RuntimeException("id inexistant");
		}
		return daoCompte.findById(id).orElseThrow(()->new RuntimeException("Compte inexistant"));
	}
	
	public Compte update(Compte compte) {
		if(compte.getId()==null) {
			throw new RuntimeException("Aucun compte avec cet id existe");
		}
				
		return daoCompte.save(compte);
	}
	
	public void delete(Compte compte) {
		if(compte.getRole()==Role.ROLE_TECHNICIEN) {
			daoTechnicien.cascadeCompteNull(compte);
		}
		if(compte.getRole()==Role.ROLE_FORMATEUR) {
			daoFormateur.cascadeCompteNull(compte);
		}
		if(compte.getRole()==Role.ROLE_GESTIONNAIRE) {
			daoGestionnaire.cascadeCompteNull(compte);
		}
		if(compte.getRole()==Role.ROLE_STAGIAIRE) {
			daoStagiaire.cascadeCompteNull(compte);
		}
		if(compte.getRole()==Role.ROLE_ADMIN) {
			throw new RuntimeException("Ce compte ne peut pas être supprimé");
		}
		daoCompte.delete(compte);
	}
	
}
