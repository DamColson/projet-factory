package ajc.formation.projet_factory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.model.VideoProjecteur;

public interface IDAOSalle extends JpaRepository<Salle, Integer>{

    @Query("update Salle s set s.video_projecteur=null where v.video_projecteur = :videoProjecteur")
    @Modifying
    @Transactional
    public void cascadeVideoProjecteurNull(@Param("videoProjecteur") VideoProjecteur videoProjecteur);
}
