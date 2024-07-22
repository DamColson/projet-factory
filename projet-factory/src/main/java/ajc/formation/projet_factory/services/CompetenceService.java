package ajc.formation.projet_factory.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ajc.formation.projet_factory.dao.IDAOCompetence;
import ajc.formation.projet_factory.dao.IDAOFormateur;
import ajc.formation.projet_factory.dao.IDAOMatiere;
import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.Matiere;

public class CompetenceService {

    @Autowired
    private IDAOCompetence daoCompetence;
    @Autowired
    private IDAOMatiere daoMatiere;
    @Autowired
    private IDAOFormateur daoFormateur;

    public List<Competence> getAll(){
        return daoCompetence.findAll();
    }

    public Competence getById(Integer id){
        return daoCompetence.findById(id).orElseThrow();
    }

    public Competence insert(Competence competence){
        return daoCompetence.save(competence);
    }

    public Competence update(Competence competence){
        if(competence.getId() == null){
            throw new RuntimeException("l'id n'existe pas");
        }
        return daoCompetence.save(competence);
    }

    public void delete(Competence competence){
        List<Formateur> formateurs = daoFormateur.findByCompetence(competence);
        formateurs = formateurs.stream().peek(formateur->formateur.getCompetences().remove(competence)).collect(Collectors.toList());
        daoFormateur.saveAll(formateurs);

        List<Matiere> matieres = daoMatiere.findByCompetence(competence);
		matieres = matieres.stream().peek(matiere->matiere.getCompetences().remove(competence)).collect(Collectors.toList());
		daoMatiere.saveAll(matieres);
        daoCompetence.delete(competence);
    }
}
