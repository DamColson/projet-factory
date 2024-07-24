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
	private IDAOBloc daoBloc;
	@Autowired
	private IDAOCompetence daoCompetence;
	@Autowired
	private IDAOOrdinateur daoOrdinateur;
	@Autowired
	private IDAOVideoProjecteur daoVideoProjecteur;

	
	
	public List<Formateur> getAll(){
		return daoFormateur.findAll();
	}
	
	public Formateur getById(Integer id) {
		return daoFormateur.findById(id).orElseThrow();
	}
	
	public Formateur insert(Formateur formateur) {
		if(formateur.getCompte()==null) 
		{
			throw new RuntimeException("Impossible d'update un formateur sans compte");
		}
		if(formateur.getOrdinateur()!=null) {
			daoOrdinateur.setOrdinateurIndisponible(formateur.getOrdinateur().getId());
		}
		if(formateur.getVideoProjecteur()!=null) {
	    	System.out.println("------------------------");
	    	System.out.println(formateur.getVideoProjecteur().getId());
	    	System.out.println("------------------------");
			daoVideoProjecteur.setVideoProjecteurIndisponible(formateur.getVideoProjecteur().getId());
		}
		return daoFormateur.save(formateur);
	}
	
	public Formateur update(Formateur formateur) {
		if(formateur.getId() == null) {
			throw new RuntimeException("l'id n'existe pas");
		}
		if(formateur.getCompte()==null) 
		{
			throw new RuntimeException("Impossible d'update un formateur sans compte");
		}
		if(formateur.getOrdinateur()!=null) {
			daoOrdinateur.setOrdinateurIndisponible(formateur.getOrdinateur().getId());
		}
		if(formateur.getVideoProjecteur()!=null) {
			daoVideoProjecteur.setVideoProjecteurIndisponible(formateur.getVideoProjecteur().getId());
		}
		return daoFormateur.save(formateur);
	}
	
	public void delete(Formateur formateur) {
		if(formateur.getOrdinateur()!=null) {
			daoOrdinateur.setOrdinateurDisponible(formateur.getOrdinateur().getId());
		}
		if(formateur.getVideoProjecteur()!=null) {
			daoVideoProjecteur.setVideoProjecteurDisponible(formateur.getVideoProjecteur().getId());
		}
		daoBloc.cascadeFormateurNull(formateur);
		List<Competence> competences = daoCompetence.findByFormateurs(formateur);
		competences = competences.stream().peek(competence->competence.getFormateurs().remove(formateur)).collect(Collectors.toList());
		daoCompetence.saveAll(competences);
		daoFormateur.delete(formateur);

	}
	
	
}
