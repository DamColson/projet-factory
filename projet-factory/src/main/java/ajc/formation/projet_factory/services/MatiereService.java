package ajc.formation.projet_factory.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOBloc;
import ajc.formation.projet_factory.dao.IDAOCompetence;
import ajc.formation.projet_factory.dao.IDAOMatiere;
import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.model.Matiere;

@Service
public class MatiereService {

    @Autowired
    private IDAOMatiere daoMatiere;
    @Autowired
    private IDAOBloc daoBloc;
    @Autowired
    private IDAOCompetence daoCompetence;

    public List<Matiere> getAll(){
        return daoMatiere.findAll();
    }

    public Matiere getById(Integer id){
        return daoMatiere.findById(id).orElseThrow();
    }

    public Matiere insert(Matiere matiere){
        return daoMatiere.save(matiere);
    }

    public Matiere update(Matiere matiere){
        if(matiere.getId() == null){
            throw new RuntimeException("l'id n'existe pas");
        }
        return daoMatiere.save(matiere);
    }

    public void delete(Matiere matiere){
        daoBloc.cascadeMatiereNull(matiere);

        List<Competence> competences = daoCompetence.findByMatiere(matiere);
		competences = competences.stream().peek(competence->competence.getMatieres().remove(matiere)).collect(Collectors.toList());
		daoCompetence.saveAll(competences);

        daoMatiere.delete(matiere);
    }

}
