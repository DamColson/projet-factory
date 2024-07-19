package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.dao.IDAOTechnicien;
import ajc.formation.projet_factory.model.Technicien;

@Service
public class TechnicienService {

	@Autowired
	IDAOTechnicien daoTechnicien;
	
	@Autowired
	IDAOOrdinateur daoOrdinateur;
	
	public List<Technicien> getAll(){
		return daoTechnicien.findAll();
	}
	
	public Technicien getById(Integer id) {
		return daoTechnicien.findById(id).orElseThrow(()->new RuntimeException("Aucun technicien avec cet id existe"));
	}
	
	public Technicien insert(Technicien technicien) {
		return daoTechnicien.save(technicien);
	}
	
	public Technicien update(Technicien technicien) {
		if(technicien.getId()==null) {
			throw new RuntimeException("Aucun technicien avec cet id existe");
		}
		return daoTechnicien.save(technicien);
	}
	
	public void delete(Technicien technicien) {
		daoOrdinateur.cascadeTechNull(technicien);
		daoTechnicien.delete(technicien);
	}
	
	public void deleteById(Integer id) {
		Technicien technicien = daoTechnicien.findById(id).orElseThrow(()->new RuntimeException("Aucun technicien avec cet id existe"));
		daoOrdinateur.cascadeTechNull(technicien);
		daoTechnicien.deleteById(id);
	}
	
	
}
