package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ajc.formation.projet_factory.model.Technicien;

public interface IDAOTechnicien extends JpaRepository<Technicien, Integer> {

}
