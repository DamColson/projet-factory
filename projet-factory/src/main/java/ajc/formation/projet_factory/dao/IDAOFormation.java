package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.projet_factory.model.Formation;

public interface IDAOFormation extends JpaRepository<Formation, Integer> {

}
