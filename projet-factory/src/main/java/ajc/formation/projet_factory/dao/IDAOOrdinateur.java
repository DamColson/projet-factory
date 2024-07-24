package ajc.formation.projet_factory.dao;


import java.util.List;

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
	
	@Query("select o from Ordinateur o left join fetch o.salle where o.salle is null")
	public List<Ordinateur> findFreeOrdinateurSalle();
	
	@Query("update Ordinateur o set o.status='INDISPONIBLE' where o.id=:id")
	@Modifying
	@Transactional
	public void setOrdinateurIndisponible(@Param("id") Integer id);
	
	@Query("update Ordinateur o set o.status='DISPONIBLE' where o.id=:id")
	@Modifying
	@Transactional
	public void setOrdinateurDisponible(@Param("id") Integer id);
	
	@Query("select o from Ordinateur o where o.status='DISPONIBLE'")
	public List<Ordinateur> findAllDisponible();
	
	@Query("select o from Ordinateur o where o.status='INDISPONIBLE'")
	public List<Ordinateur> findAllIndisponible();
	
}
