package ajc.formation.projet_factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.model.Matiere;

public interface IDAOMatiere extends JpaRepository<Matiere, Integer>{

	@Query("select m from Matiere m left join fetch m.competences as c where c =:competence")
	public List<Matiere> findByCompetence(@Param("competence") Competence competence);
}
