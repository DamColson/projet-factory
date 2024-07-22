package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ajc.formation.projet_factory.dao.IDAOFormation;
import ajc.formation.projet_factory.dao.IDAOGestionnaire;
import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.model.Gestionnaire;

public class GestionnaireService {

    @Autowired
    private IDAOGestionnaire daoGestionnaire;
    @Autowired
    private IDAOOrdinateur daoOrdinateur;
    @Autowired
    private IDAOFormation daoFormation;

    public List<Gestionnaire> getAll(){
        return daoGestionnaire.findAll();
    }

    public Gestionnaire getById(Integer id){
        return daoGestionnaire.findById(id).orElseThrow();
    }

    public Gestionnaire insert(Gestionnaire gestionnaire){
        return daoGestionnaire.save(gestionnaire);
    }

    public Gestionnaire update(Gestionnaire gestionnaire){
        if(gestionnaire.getId() == null){
            throw new RuntimeException("l'id n'existe pas");
        }
        return daoGestionnaire.save(gestionnaire);
    }

    public void delete(Gestionnaire gestionnaire){
        daoOrdinateur.cascadeGestionnaireNull(gestionnaire);
        daoFormation.cascadeGestionnaireNull(gestionnaire);

        daoGestionnaire.delete(gestionnaire);
    }
    
}
