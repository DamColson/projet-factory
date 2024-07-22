package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Stagiaire;

public interface IDAOStagiaire extends JpaRepository<Stagiaire, Integer>{

	@Query("update Stagiaire s set s.compte=null where s.compte=:compte")
	@Modifying
	@Transactional
	public void cascadeCompteNull(@Param("compte") Compte compte);
}
