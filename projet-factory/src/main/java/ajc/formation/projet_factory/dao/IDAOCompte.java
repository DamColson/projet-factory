package ajc.formation.projet_factory.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Role;


public interface IDAOCompte extends JpaRepository<Compte, Integer>{

	public Optional<Compte> findByLogin(String username);
	
	@Query("select c from Compte c left join fetch c.technicien where c.role='ROLE_TECHNICIEN' and c.technicien is null")
	public List<Compte> findFreeTechnicien();
	
	@Query("select c from Compte c left join fetch c.formateur where c.role='ROLE_FORMATEUR' and c.formateur is null")
	public List<Compte> findFreeFormateur();

	@Query("select c from Compte c left join fetch c.gestionnaire where c.role='ROLE_GESTIONNAIRE' and c.gestionnaire is null")
	public List<Compte> findFreeGestionnaire();
	
	@Query("select c from Compte c left join fetch c.stagiaire where c.role='ROLE_STAGIAIRE' and c.stagiaire is null")
	public List<Compte> findFreeStagiaire();
	
	public List<Compte> findByRole(Role role);
	
}
