package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Formateur;

public interface IDAOFormateur extends JpaRepository<Formateur, Integer> {

	@Query("update Formateur f set f.compte=null where f.compte=:compte")
	@Transactional
	@Modifying
	public void cascadeCompteNull(@Param("compte") Compte compte);
}
