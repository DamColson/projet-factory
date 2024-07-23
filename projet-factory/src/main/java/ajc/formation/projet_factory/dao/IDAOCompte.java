package ajc.formation.projet_factory.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.Gestionnaire;
import ajc.formation.projet_factory.model.Stagiaire;
import ajc.formation.projet_factory.model.Technicien;
import jakarta.transaction.Transactional;

public interface IDAOCompte extends JpaRepository<Compte, Integer>{

	public Optional<Compte> findByLogin(String username);

	@Query("update Compte c set c.technicien=null where c.technicien = :technicien")
	@Modifying
	@Transactional
	public void cascadeTechNull(@Param("technicien") Technicien technicien);
	
	@Query("update Compte c set c.formateur=null where c.formateur = :formateur")
	@Modifying
	@Transactional
	public void cascadeFormNull(@Param("formateur") Formateur formateur);
	
	@Query("update Compte c set c.gestionnaire=null where c.gestionnaire = :gestionnaire")
	@Modifying
	@Transactional
	public void cascadeGestNull(@Param("gestionnaire") Gestionnaire gestionnaire);
	
	@Query("update Compte c set c.stagiaire=null where c.stagiaire = :stagiaire")
	@Modifying
	@Transactional
	public void cascadeStagNull(@Param("stagiaire") Stagiaire stagiaire);
	
	@Query("select c from Compte c left join fetch c.technicien where c.role='ROLE_TECHNICIEN' and c.technicien is null")
	public List<Compte> findFreeTechnicien();
	
	@Query("select c from Compte c left join fetch c.formateur where c.role='ROLE_FORMATEUR' and c.formateur is null")
	public List<Compte> findFreeFormateur();

	@Query("select c from Compte c left join fetch c.gestionnaire where c.role='ROLE_GESTIONNAIRE' and c.gestionnaire is null")
	public List<Compte> findFreeGestionnaire();
	
	@Query("select c from Compte c left join fetch c.stagiaire where c.role='ROLE_STAGIAIRE' and c.stagiaire is null")
	public List<Compte> findFreeStagiaire();
	
}
