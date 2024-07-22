package ajc.formation.projet_factory.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Technicien;

public interface IDAOTechnicien extends JpaRepository<Technicien, Integer> {

	@Query("select t from Technicien t left join fetch t.ordinateur where t.id=:id ")
	public Optional<Technicien> findByIdFetchOrdinateur(@Param("id") Integer id);
	
	@Query("update Technicien t set t.compte=null where t.compte=:compte")
	@Transactional
	@Modifying
	public void cascadeCompteNull(@Param("compte") Compte compte);

}
