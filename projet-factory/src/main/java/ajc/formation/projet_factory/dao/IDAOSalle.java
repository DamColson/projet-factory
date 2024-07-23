package ajc.formation.projet_factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Salle;

public interface IDAOSalle extends JpaRepository<Salle, Integer>{
    @Query("select s from Salle s left join fetch s.ordinateurs as o where o =:ordinateur")
    public List<Salle> findByOrdinateur(@Param("ordinateur") Ordinateur ordinateur);
}
