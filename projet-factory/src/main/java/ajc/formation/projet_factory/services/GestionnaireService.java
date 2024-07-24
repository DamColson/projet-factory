package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOFormation;
import ajc.formation.projet_factory.dao.IDAOGestionnaire;
import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.model.Gestionnaire;

@Service
public class GestionnaireService {

    @Autowired
    private IDAOGestionnaire daoGestionnaire;
    @Autowired
    private IDAOFormation daoFormation;
    @Autowired
    private IDAOOrdinateur daoOrdinateur;

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
        if(gestionnaire.getCompte()==null) 
		{
			throw new RuntimeException("Impossible d'update un gestionnaire sans compte");
		}
        if(gestionnaire.getOrdinateur()!=null) {
        	daoOrdinateur.setOrdinateurIndisponible(gestionnaire.getOrdinateur().getId());
        }
        return daoGestionnaire.save(gestionnaire);
    }

    public void delete(Gestionnaire gestionnaire){
    	if(gestionnaire.getOrdinateur()!=null) {
        	daoOrdinateur.setOrdinateurDisponible(gestionnaire.getOrdinateur().getId());
        }
        daoFormation.cascadeGestionnaireNull(gestionnaire);

        daoGestionnaire.delete(gestionnaire);
    }
    
}
