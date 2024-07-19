package ajc.formation.projet_factory.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Technicien;

public interface IDAOTechnicien extends JpaRepository<Technicien, Integer> {

	@Query("select t from Technicien t left join fetch t.ordinateur where t.id=:id ")
	public Optional<Technicien> findByIdFetchOrdinateur(@Param("id") Integer id);

}
