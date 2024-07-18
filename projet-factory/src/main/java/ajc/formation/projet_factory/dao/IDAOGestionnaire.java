package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.projet_factory.model.Gestionnaire;

public interface IDAOGestionnaire extends JpaRepository<Gestionnaire, Integer>{

}
