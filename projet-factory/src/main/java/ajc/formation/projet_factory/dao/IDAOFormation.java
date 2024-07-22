package ajc.formation.projet_factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Bloc;
import ajc.formation.projet_factory.model.Formation;
import ajc.formation.projet_factory.model.Gestionnaire;
import jakarta.transaction.Transactional;

public interface IDAOFormation extends JpaRepository<Formation, Integer> {

	@Query("select f from Formation f left join fetch f.blocs as b where b =:bloc")
	public List<Formation> findByBloc(@Param("bloc") Bloc bloc);

	@Query("update Formation f set f.gestionnaire=null where f.gestionnaire = :gestionnaire")
	@Modifying
	@Transactional
	public void cascadeGestionnaireNull(@Param("gestionnaire") Gestionnaire gestionnaire);
}
