package ajc.formation.projet_factory.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOBloc;
import ajc.formation.projet_factory.dao.IDAOFormation;
import ajc.formation.projet_factory.dao.IDAOStagiaire;
import ajc.formation.projet_factory.model.Bloc;
import ajc.formation.projet_factory.model.Formation;

@Service
public class FormationService {

    @Autowired
    private IDAOFormation daoFormation;
    @Autowired
    private IDAOStagiaire daoStagiaire;
    @Autowired
    private IDAOBloc daoBloc;

    public List<Formation> getAll(){
        return daoFormation.findAll();
    }

    public Formation getById(Integer id){
        return daoFormation.findById(id).orElseThrow();
    }

    public Formation insert(Formation formation){
        return daoFormation.save(formation);
    }

    public Formation update(Formation formation){
        if(formation.getId() == null){
            throw new RuntimeException("l'id n'existe pas");
        }
        return daoFormation.save(formation);
    }

    public void delete(Formation formation){
        daoStagiaire.cascadeFormationtNull(formation);
        List<Bloc> blocs = daoBloc.findByFormation(formation);
        blocs = blocs.stream().peek(bloc -> bloc.getFormations().remove(formation)).collect(Collectors.toList());
        daoBloc.saveAll(blocs);
        daoFormation.delete(formation);
    }
}
