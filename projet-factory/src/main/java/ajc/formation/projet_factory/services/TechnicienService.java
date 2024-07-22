package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOCompte;
import ajc.formation.projet_factory.dao.IDAOOrdinateur;
import ajc.formation.projet_factory.dao.IDAOTechnicien;
import ajc.formation.projet_factory.model.Technicien;

@Service
public class TechnicienService {

	@Autowired
	IDAOTechnicien daoTechnicien;
	
	@Autowired
	IDAOOrdinateur daoOrdinateur;
	
	@Autowired
	IDAOCompte daoCompte;
	
	public List<Technicien> getAll(){
		return daoTechnicien.findAll();
	}
	
	public Technicien getById(Integer id) {
		if(id==null) {
			throw new RuntimeException("Aucun technicien avec cet id existe");
		}
		return daoTechnicien.findById(id).orElseThrow(()->new RuntimeException("Aucun technicien avec cet id existe"));
	}
	
	public Technicien getByIdWithOrdinateur(Integer id) {
		if(id==null) {
			throw new RuntimeException("Aucun technicien avec cet id existe");
		}
		return daoTechnicien.findByIdFetchOrdinateur(id).orElseThrow(()->new RuntimeException("Aucun technicien avec cet id existe"));
	}
	
	public Technicien insert(Technicien technicien) {
		return daoTechnicien.save(technicien);
	}
	
	public Technicien update(Technicien technicien) {
		if(technicien.getId()==null) {
			throw new RuntimeException("Aucun technicien avec cet id existe");
		}
		if(technicien.getCompte()==null) 
		{
			throw new RuntimeException("Impossible d'update un technicien sans compte");
		}
		if(technicien.getCompte().getId()==null) 
		{
			throw new RuntimeException("Impossible d'update un technicien avec un compte sans id");
		}		
		return daoTechnicien.save(technicien);
	}
	
	public void delete(Technicien technicien) {
		daoOrdinateur.cascadeTechNull(technicien);
		daoCompte.cascadeTechNull(technicien);
		daoTechnicien.delete(technicien);
	}
	
	public void deleteById(Integer id) {
		if(id==null) {
			throw new RuntimeException("Aucun technicien avec cet id existe");
		}
		Technicien technicien = daoTechnicien.findById(id).orElseThrow(()->new RuntimeException("Aucun technicien avec cet id existe"));
		daoOrdinateur.cascadeTechNull(technicien);
		daoCompte.cascadeTechNull(technicien);
		daoTechnicien.deleteById(id);
	}
	
	
}
