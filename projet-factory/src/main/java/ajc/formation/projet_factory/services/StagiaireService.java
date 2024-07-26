package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.dao.IDAOStagiaire;
import ajc.formation.projet_factory.model.Formation;
import ajc.formation.projet_factory.model.Stagiaire;

@Service
public class StagiaireService {

    @Autowired
    private IDAOStagiaire daoStagiaire;
    @Autowired
    private IDAOOrdinateur daoOrdinateur;

    public List<Stagiaire> getAll(){
        return daoStagiaire.findAll();
    }

    public Stagiaire getById(Integer id){
        return daoStagiaire.findById(id).orElseThrow();
    }

    public void setFormationNull(Formation formation,Integer id) {
    	daoStagiaire.setFormationNull(formation,id);
    }
    
    public List<Stagiaire> getByFormation(Formation formation){
    	return daoStagiaire.findByFormation(formation);
    }
    public Stagiaire insert(Stagiaire stagiaire){
        return daoStagiaire.save(stagiaire);
    }

    public Stagiaire update(Stagiaire stagiaire){
        if(stagiaire.getId() == null){
            throw new RuntimeException("l'id n'existe pas");
        }
        if(stagiaire.getCompte()==null) 
		{
			throw new RuntimeException("Impossible d'update un stagiaire sans compte");
		}
        if(stagiaire.getOrdinateur()!=null) {
        	daoOrdinateur.setOrdinateurIndisponible(stagiaire.getOrdinateur().getId());
        }
        return daoStagiaire.save(stagiaire);
    }

    public void delete(Stagiaire stagiaire){
    	if(stagiaire.getOrdinateur()!=null) {
        	daoOrdinateur.setOrdinateurDisponible(stagiaire.getOrdinateur().getId());
        }
        daoStagiaire.delete(stagiaire);
    }

}
