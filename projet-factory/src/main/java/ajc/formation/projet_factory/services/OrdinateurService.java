package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.model.Ordinateur;

@Service
public class OrdinateurService {

	@Autowired
	IDAOOrdinateur daoOrdinateur;

	public List<Ordinateur> getAll() {
		return daoOrdinateur.findAll();
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
	
	
}
