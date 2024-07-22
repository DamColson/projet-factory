package ajc.formation.projet_factory.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ajc.formation.projet_factory.dao.IDAOBloc;
import ajc.formation.projet_factory.dao.IDAOFormation;
import ajc.formation.projet_factory.model.Bloc;
import ajc.formation.projet_factory.model.Formation;

public class BlocService {

    @Autowired
    private IDAOBloc daoBloc;

    @Autowired
    private IDAOFormation daoFormation;


    public List<Bloc> getAll(){
        return daoBloc.findAll();
    }

    public Bloc getById(Integer id){
        return daoBloc.findById(id).orElseThrow();
    }

    public Bloc insert(Bloc bloc){
        return daoBloc.save(bloc);
    }

    public Bloc update(Bloc bloc){
        if(bloc.getId() == null){
            throw new RuntimeException("l'id n'existe pas");
        }
        return daoBloc.save(bloc);
    }

    public void delete(Bloc bloc){

        List<Formation> formations = daoFormation.findByBloc(bloc);
        formations = formations.stream().peek(formation->formation.getBlocs().remove(bloc)).collect(Collectors.toList());
        daoFormation.saveAll(formations);
        daoBloc.delete(bloc);
    }
}
