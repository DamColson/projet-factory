package ajc.formation.projet_factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Bloc;
import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.Formation;
import ajc.formation.projet_factory.model.Matiere;
import jakarta.transaction.Transactional;

public interface IDAOBloc extends JpaRepository<Bloc, Integer> {

	@Query("update Bloc b set b.formateur=null where b.formateur = :formateur")
	@Modifying
	@Transactional
	public void cascadeFormateurNull(@Param("formateur") Formateur formateur);

	@Query("update Bloc b set b.matiere=null where b.matiere = :matiere")
	@Modifying
	@Transactional
	public void cascadeMatiereNull(@Param("matiere") Matiere matiere);

	@Query("select b from Bloc b left join fetch b.formations as f where f =:formation")
	public List<Bloc> findByFormation(@Param("formation") Formation formation);
}
