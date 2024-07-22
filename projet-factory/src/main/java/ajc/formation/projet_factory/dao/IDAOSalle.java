package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.projet_factory.model.Salle;

public interface IDAOSalle extends JpaRepository<Salle, Integer>{
}
