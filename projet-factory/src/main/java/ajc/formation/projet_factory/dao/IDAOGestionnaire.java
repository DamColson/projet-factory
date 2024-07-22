package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Gestionnaire;

public interface IDAOGestionnaire extends JpaRepository<Gestionnaire, Integer>{

	
	@Transactional
	@Modifying
	@Query("update Gestionnaire g set g.compte=null where g.compte=:compte")
	public void cascadeCompteNull(@Param("compte") Compte compte);
}
