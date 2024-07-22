package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOStagiaire;
import ajc.formation.projet_factory.model.Stagiaire;

@Service
public class StagiaireService {

    @Autowired
    private IDAOStagiaire daoStagiaire;

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
        daoStagiaire.delete(stagiaire);
    }

}
