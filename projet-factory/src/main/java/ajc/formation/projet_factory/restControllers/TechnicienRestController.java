package ajc.formation.projet_factory.restControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.dto.request.TechnicienRequest;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.dto.response.TechnicienResponse;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Technicien;
import ajc.formation.projet_factory.services.CompteService;
import ajc.formation.projet_factory.services.OrdinateurService;
import ajc.formation.projet_factory.services.TechnicienService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/technicien")
@CrossOrigin("*")
public class TechnicienRestController {
	
	@Autowired
	TechnicienService technicienSrv;
	@Autowired
	OrdinateurService ordinateurSrv;
	@Autowired
	CompteService compteSrv;

	@GetMapping("")
	@JsonView(CustomJsonViews.TechnicienWithOrdinateur.class)
	public List<TechnicienResponse> getAll(){
		return technicienSrv.getAll().stream().map(technicien->new TechnicienResponse(technicien)).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@JsonView(CustomJsonViews.TechnicienWithOrdinateur.class)
	public TechnicienResponse getById(@PathVariable("id") Integer id) {
		return new TechnicienResponse(technicienSrv.getByIdWithOrdinateur(id));
	}
	
	@PostMapping("")
	@ResponseStatus(code=HttpStatus.CREATED)
	@JsonView(CustomJsonViews.TechnicienWithOrdinateur.class)
	public TechnicienResponse Create(@Valid @RequestBody TechnicienRequest technicienRequest,BindingResult br) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Technicien technicien = new Technicien();
		BeanUtils.copyProperties(technicienRequest, technicien);
		Ordinateur ordinateur = ordinateurSrv.getById(technicienRequest.getOrdinateurId());
		technicien.setOrdinateur(ordinateur);
		Compte compte = compteSrv.getById(technicienRequest.getCompteId());
		technicien.setCompte(compte);
		return new TechnicienResponse(technicienSrv.insert(technicien));	
	}
	
	@PutMapping("/{id}")
	@JsonView(CustomJsonViews.TechnicienWithOrdinateur.class)
	public TechnicienResponse update(@Valid @RequestBody TechnicienRequest technicienRequest,BindingResult br, @PathVariable("id") Integer id) {
		if(br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Technicien technicien = new Technicien();
		BeanUtils.copyProperties(technicienRequest, technicien);
		Ordinateur ordinateur = ordinateurSrv.getById(technicienRequest.getOrdinateurId());				
		technicien.setOrdinateur(ordinateur);
		technicien.setId(id);
		Compte compte = compteSrv.getById(technicienRequest.getCompteId());
		technicien.setCompte(compte);
		return new TechnicienResponse(technicienSrv.update(technicien));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		technicienSrv.delete(technicienSrv.getById(id));
	}
	
}
