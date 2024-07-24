package ajc.formation.projet_factory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ajc.formation.projet_factory.dao.IDAOFormateur;
import ajc.formation.projet_factory.dao.IDAOGestionnaire;
import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.dao.IDAOSalle;
import ajc.formation.projet_factory.dao.IDAOStagiaire;
import ajc.formation.projet_factory.dao.IDAOTechnicien;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Salle;

@Service
@Transactional
public class OrdinateurService {

	@Autowired
	IDAOOrdinateur daoOrdinateur;
	@Autowired
	IDAOSalle daoSalle;
	@Autowired
	IDAOFormateur daoFormateur;
	@Autowired
	IDAOGestionnaire daoGestionnaire;
	@Autowired
	IDAOStagiaire daoStagiaire;
	@Autowired
	IDAOTechnicien daoTechnicien;


	public List<Ordinateur> getAll() {
		return daoOrdinateur.findAll();
	}
	
	public List<Ordinateur> getAllDisponible(){
		return daoOrdinateur.findAllDisponible();
	}
	
	public List<Ordinateur> getAllIndisponible(){
		return daoOrdinateur.findAllIndisponible();
	}
	
	public List<Ordinateur> getAllFreeSalle(){
		return daoOrdinateur.findFreeOrdinateurSalle();
	}
	
	
	public Ordinateur getById(Integer id) {
		if(id==null) {
			throw new RuntimeException("id inexistant");
		}
		
		return daoOrdinateur.findById(id).orElseThrow(()->new RuntimeException("Ordinateur inexistant"));
	}
	
	public Ordinateur insert(Ordinateur ordinateur) {
		return daoOrdinateur.save(ordinateur);
	}
	
	
	public Ordinateur update(Ordinateur ordinateur){
		if(ordinateur.getId()==null) {
			throw new RuntimeException("Aucune salle avec cet id existe");
		}
		return daoOrdinateur.save(ordinateur);
	}

	public void delete(Ordinateur ordinateur) {
		daoFormateur.cascadeOrdinateurNull(ordinateur);
		daoGestionnaire.cascadeOrdinateurNull(ordinateur);
		daoStagiaire.cascadeOrdinateurNull(ordinateur);
		daoTechnicien.cascadeOrdniateurNull(ordinateur);

		List<Salle> salles = daoSalle.findByOrdinateur(ordinateur);
		salles = salles.stream().peek(salle->salle.getOrdinateurs().remove(ordinateur)).collect(Collectors.toList());
		daoSalle.saveAll(salles);
		
		daoOrdinateur.delete(ordinateur);
	}
	
}
