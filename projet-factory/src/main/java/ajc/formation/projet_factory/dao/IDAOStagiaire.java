package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.projet_factory.model.Stagiaire;

public interface IDAOStagiaire extends JpaRepository<Stagiaire, Integer>{

}
