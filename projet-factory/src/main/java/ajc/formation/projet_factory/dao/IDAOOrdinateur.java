package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Technicien;
import jakarta.transaction.Transactional;

public interface IDAOOrdinateur extends JpaRepository<Ordinateur, Integer>{

	@Query("update Ordinateur o set o.formateur=null where o.formateur = :formateur")
	@Modifying
	@Transactional
	public void cascadeNull(@Param("formateur") Formateur formateur);
	
	@Query("update Ordinateur o set o.technicien=null where o.technicien = :technicien")
	@Modifying
	@Transactional
	public void cascadeTechNull(@Param("technicien") Technicien technicien);
}
