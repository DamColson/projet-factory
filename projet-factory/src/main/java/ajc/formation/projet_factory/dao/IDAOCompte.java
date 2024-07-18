package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.projet_factory.model.Compte;

public interface IDAOCompte extends JpaRepository<Compte, Integer>{

}
