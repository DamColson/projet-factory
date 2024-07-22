package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ajc.formation.projet_factory.dao.IDAOFormation;
import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.dao.IDAOStagiaire;
import ajc.formation.projet_factory.model.Stagiaire;

public class StagiaireService {

    @Autowired
    private IDAOStagiaire daoStagiaire;
    @Autowired
    private IDAOOrdinateur daoOrdinateur;
    @Autowired
    private IDAOFormation daoFormation;


    public List<Stagiaire> getAll(){
        return daoStagiaire.findAll();
    }

    public Stagiaire getById(Integer id){
        return daoStagiaire.findById(id).orElseThrow();
    }

    public Stagiaire insert(Stagiaire stagiaire){
        return daoStagiaire.save(stagiaire);
    }

    public Stagiaire update(Stagiaire stagiaire){
        if(stagiaire.getId() == null){
            throw new RuntimeException("l'id n'existe pas");
        }
        return daoStagiaire.save(stagiaire);
    }

    public void delete(Stagiaire stagiaire){
        daoFormation.cascadeStagiaireNull(stagiaire);
        daoOrdinateur.cascadeStagiaireNull(stagiaire);

        daoStagiaire.delete(stagiaire);
    }

}
