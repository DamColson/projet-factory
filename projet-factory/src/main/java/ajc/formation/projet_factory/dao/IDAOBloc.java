package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Bloc;
import ajc.formation.projet_factory.model.Formateur;
import jakarta.transaction.Transactional;

public interface IDAOBloc extends JpaRepository<Bloc, Integer> {

	@Query("update Bloc b set b.formateur=null where b.formateur = :formateur")
	@Modifying
	@Transactional
	public void cascadeNull(@Param("formateur") Formateur formateur);
}
