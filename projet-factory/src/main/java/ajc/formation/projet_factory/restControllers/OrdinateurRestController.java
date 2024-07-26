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

import ajc.formation.projet_factory.dto.request.OrdinateurRequest;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.dto.response.OrdinateurResponse;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.model.Status;
import ajc.formation.projet_factory.services.OrdinateurService;
import ajc.formation.projet_factory.services.SalleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ordinateur")
@CrossOrigin("*")
@SecurityRequirement(name = "basicAuth")
public class OrdinateurRestController {
    @Autowired
    private OrdinateurService ordinateurService;
    @Autowired
    private SalleService salleService;

    @GetMapping("")
    @JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
    public List<OrdinateurResponse> getAll() {
        return ordinateurService.getAll().stream().map(ordinateur->new OrdinateurResponse(ordinateur)).collect(Collectors.toList());
    }
    
    @GetMapping("/free-salle")
    @JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
    public List<OrdinateurResponse> getAllFreeSalle(){
    	return ordinateurService.getAllFreeSalle().stream().map(ordinateur->new OrdinateurResponse(ordinateur)).collect(Collectors.toList());
    }
    
    @GetMapping("/disponible")
    @JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
    public List<OrdinateurResponse> getAllDisponible(){
    	return ordinateurService.getAllDisponible().stream().map(ordinateur->new OrdinateurResponse(ordinateur)).collect(Collectors.toList());
    }
    
    @GetMapping("/indisponible")
    @JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
    public List<OrdinateurResponse> getAllIndisponible(){
    	return ordinateurService.getAllIndisponible().stream().map(ordinateur->new OrdinateurResponse(ordinateur)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
    public OrdinateurResponse getById(@PathVariable("id") Integer id) {
        return new OrdinateurResponse(ordinateurService.getById(id));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
    public OrdinateurResponse create(@Valid @RequestBody OrdinateurRequest ordinateurRequest, BindingResult br) {
       if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Ordinateur ordinateur = new Ordinateur();
        BeanUtils.copyProperties(ordinateurRequest, ordinateur,"status");
        ordinateur.setStatus(Status.valueOf(ordinateurRequest.getStatus()));

        if(ordinateurRequest.getSalleId() != null){
            Salle salle = salleService.getById(ordinateurRequest.getSalleId());
            ordinateur.setSalle(salle);
        }

        return new OrdinateurResponse(ordinateurService.insert(ordinateur));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.OrdinateurWithAttributes.class)
    public OrdinateurResponse update(@Valid @RequestBody OrdinateurRequest ordinateurRequest, BindingResult br, @PathVariable Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Ordinateur ordinateur = new Ordinateur();
        BeanUtils.copyProperties(ordinateurRequest, ordinateur,"status");
        ordinateur.setStatus(Status.valueOf(ordinateurRequest.getStatus()));

        if(ordinateurRequest.getSalleId() != null){
            Salle salle = salleService.getById(ordinateurRequest.getSalleId());
            ordinateur.setSalle(salle);
        }
        ordinateur.setId(id);
        return new OrdinateurResponse(ordinateurService.update(ordinateur));
    }

    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		ordinateurService.delete(ordinateurService.getById(id));
	}
}
