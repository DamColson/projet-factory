package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOBloc;
import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.dao.IDAOSalle;
import ajc.formation.projet_factory.dao.IDAOVideoProjecteur;
import ajc.formation.projet_factory.model.Salle;

@Service
public class SalleService {

	@Autowired
	private IDAOSalle daoSalle;
	@Autowired
	private IDAOOrdinateur daoOrdinateur;
	@Autowired
	private IDAOBloc daoBloc;
	@Autowired
	private IDAOVideoProjecteur daoVideoProjecteur;
	
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
	
	public Salle update(Salle salle) {
		if(salle.getId()==null) {
			throw new RuntimeException("Aucune salle avec cet id existe");
		}
		
		return daoSalle.save(salle);
	}
	
	public void delete(Salle salle) {
		daoOrdinateur.cascadeSalleNull(salle);
		daoBloc.cascadeSalleNull(salle);
		daoVideoProjecteur.cascadeSalleNull(salle);

		daoSalle.delete(salle);
	}
}


