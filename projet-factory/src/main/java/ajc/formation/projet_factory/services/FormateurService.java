package ajc.formation.projet_factory.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOBloc;
import ajc.formation.projet_factory.dao.IDAOCompetence;
import ajc.formation.projet_factory.dao.IDAOFormateur;
import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.dao.IDAOVideoProjecteur;
import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.model.Formateur;

@Service
public class FormateurService {

	
	@Autowired
	private IDAOFormateur daoFormateur;
	@Autowired
	private IDAOOrdinateur daoOrdinateur;
	@Autowired
	private IDAOBloc daoBloc;
	@Autowired
	private IDAOVideoProjecteur daoVideoProjecteur;
	@Autowired
	private IDAOCompetence daoCompetence;
	
	
	public List<Formateur> getAll(){
		return daoFormateur.findAll();
	}
	
	public Formateur getById(Integer id) {
		return daoFormateur.findById(id).orElseThrow();
	}
	
	public Formateur insert(Formateur formateur) {
		return daoFormateur.save(formateur);
	}
	
	public Formateur update(Formateur formateur) {
		if(formateur.getId() == null) {
			throw new RuntimeException("l'id n'existe pas");
		}
		return daoFormateur.save(formateur);
	}
	
	public void delete(Formateur formateur) {
		
		
		daoVideoProjecteur.cascadeNull(formateur);
		daoOrdinateur.cascadeNull(formateur);
		daoBloc.cascadeNull(formateur);
		List<Competence> competences = daoCompetence.findByFormateurs(formateur);
		competences = competences.stream().peek(competence->competence.getFormateurs().remove(formateur)).collect(Collectors.toList());
		daoCompetence.saveAll(competences);
		daoFormateur.delete(formateur);
		
		
		
		// Trouver le videoprojecteur associé au formateur => select v from VideoProjecteur v 
		// Modifier le video projecteur pour retirer le formateur
		// Supprimer le formateur
	}
	
	
}
