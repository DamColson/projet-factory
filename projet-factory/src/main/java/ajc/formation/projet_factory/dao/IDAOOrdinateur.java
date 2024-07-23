package ajc.formation.projet_factory.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Salle;
import jakarta.transaction.Transactional;

public interface IDAOOrdinateur extends JpaRepository<Ordinateur, Integer>{

	@Query("update Ordinateur o set o.salle=null where o.salle = :salle")
	@Modifying
	@Transactional
	public void cascadeSalleNull(@Param("salle") Salle salle);
}
