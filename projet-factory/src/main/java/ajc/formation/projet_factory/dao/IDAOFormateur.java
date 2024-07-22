package ajc.formation.projet_factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.VideoProjecteur;

public interface IDAOFormateur extends JpaRepository<Formateur, Integer> {
  @Query("select f from Formateur f left join fetch f.competences as c where c =:competence")
	public List<Formateur> findByCompetence(@Param("competence") Competence competence);

	@Query("update Formateur f set f.compte=null where f.compte=:compte")
	@Transactional
	@Modifying
	public void cascadeCompteNull(@Param("compte") Compte compte);

	@Query("update Formateur f set f.videoProjecteur=null where f.videoProjecteur=:videoProjecteur")
	@Transactional
	@Modifying
	public void cascadevideoProjecteurNull(@Param("videoProjecteur") VideoProjecteur videoProjecteur);
}
