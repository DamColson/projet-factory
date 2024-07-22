package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Formation;
import ajc.formation.projet_factory.model.Stagiaire;
import jakarta.transaction.Transactional;

public interface IDAOStagiaire extends JpaRepository<Stagiaire, Integer>{

    @Query("update Stagiaire s set s.formation=null where s.formation = :formation")
	@Modifying
	@Transactional
	public void cascadeFormationtNull(@Param("formation") Formation formation);
}
