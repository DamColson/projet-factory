package ajc.formation.projet_factory.services;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOSalle;
import ajc.formation.projet_factory.model.Salle;

@Service
public class SalleService {

	@Autowired
	IDAOSalle daoSalle;
	
	
	public List<Salle> getAll(){
		return daoSalle.findAll();
	}
	
	public Salle getById(Integer id) {
		if(id==null) {
			throw new RuntimeException("id inexistant");
		}
		return daoSalle.findById(id).orElseThrow(()->new RuntimeException("Salle inexistante"));
	}
	
	public Salle insert(Salle salle) {
		return daoSalle.save(salle);
	}
}


