package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.projet_factory.model.Formateur;

public interface IDAOFormateur extends JpaRepository<Formateur, Integer> {

}
