package ajc.formation.projet_factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.model.VideoProjecteur;
import jakarta.transaction.Transactional;

public interface IDAOVideoProjecteur extends JpaRepository<VideoProjecteur, Integer> {
	
	@Query("update VideoProjecteur v set v.salle=null where v.salle = :salle")
	@Modifying
	@Transactional
	public void cascadeSalleNull(@Param("salle") Salle salle);
	
	@Query("select vp from VideoProjecteur vp left join fetch vp.salle where vp.salle is null")
	public List<VideoProjecteur> findFreevpSalle();
	
	@Query("update VideoProjecteur vp set vp.status='INDISPONIBLE' where vp.id=:id")
	@Modifying
	@Transactional
	public void setVideoProjecteurIndisponible(@Param("id") Integer id);
	
	@Query("update VideoProjecteur vp set vp.status='DISPONIBLE' where vp.id=:id")
	@Modifying
	@Transactional
	public void setVideoProjecteurDisponible(@Param("id") Integer id);
	
	@Query("select vp from VideoProjecteur vp where vp.status='DISPONIBLE'")
	public List<VideoProjecteur> findAllDisponible();
	
	@Query("select vp from VideoProjecteur vp where vp.status='INDISPONIBLE'")
	public List<VideoProjecteur> findAllIndisponible();
}
