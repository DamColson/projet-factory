package ajc.formation.projet_factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.model.Formateur;

public interface IDAOCompetence extends JpaRepository<Competence, Integer>{

	@Query("select c from Competence c left join fetch c.formateurs as f where f =:formateur")
	public List<Competence> findByFormateurs(@Param("formateur") Formateur formateur);
}
