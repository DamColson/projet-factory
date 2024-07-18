package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.projet_factory.model.Matiere;

public interface IDAOMatiere extends JpaRepository<Matiere, Integer>{

}
