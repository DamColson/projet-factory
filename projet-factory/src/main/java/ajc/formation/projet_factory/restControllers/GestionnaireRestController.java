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

import ajc.formation.projet_factory.dto.request.GestionnaireRequest;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.dto.response.GestionnaireResponse;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Gestionnaire;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.services.CompteService;
import ajc.formation.projet_factory.services.GestionnaireService;
import ajc.formation.projet_factory.services.OrdinateurService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/gestionnaire")
@CrossOrigin("*")
public class GestionnaireRestController {

    @Autowired
    private GestionnaireService gestionnaireService;
    @Autowired
    private CompteService compteService;
    @Autowired
    private OrdinateurService ordinateurService;
    
    @GetMapping("")
    @JsonView(CustomJsonViews.GestionnaireWithAttributes.class)
    public List<GestionnaireResponse> getAll() {
        return gestionnaireService.getAll().stream().map(gestionnaire->new GestionnaireResponse(gestionnaire)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.GestionnaireWithAttributes.class)
    public GestionnaireResponse getById(@PathVariable("id") Integer id) {
        return new GestionnaireResponse(gestionnaireService.getById(id));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.GestionnaireWithAttributes.class)
    public GestionnaireResponse create(@Valid @RequestBody GestionnaireRequest gestionnaireRequest, BindingResult br) {
       if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Gestionnaire gestionnaire = new Gestionnaire();
        BeanUtils.copyProperties(gestionnaireRequest, gestionnaire);

        Compte compte = compteService.getById(gestionnaireRequest.getCompteId());
        gestionnaire.setCompte(compte);

        if(gestionnaireRequest.getOrdinateurId() != null){
            Ordinateur ordinateur = ordinateurService.getById(gestionnaireRequest.getOrdinateurId());
            gestionnaire.setOrdinateur(ordinateur);
        }

        return new GestionnaireResponse(gestionnaireService.insert(gestionnaire));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.GestionnaireWithAttributes.class)
    public GestionnaireResponse update(@Valid @RequestBody GestionnaireRequest gestionnaireRequest, BindingResult br, @PathVariable Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Gestionnaire gestionnaire = new Gestionnaire();
        BeanUtils.copyProperties(gestionnaireRequest, gestionnaire);

        Compte compte = compteService.getById(gestionnaireRequest.getCompteId());
        gestionnaire.setCompte(compte);

        if(gestionnaireRequest.getOrdinateurId() != null){
            Ordinateur ordinateur = ordinateurService.getById(gestionnaireRequest.getOrdinateurId());
            gestionnaire.setOrdinateur(ordinateur);
        }
        gestionnaire.setId(id);

        return new GestionnaireResponse(gestionnaireService.update(gestionnaire));
    }

    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		gestionnaireService.delete(gestionnaireService.getById(id));
	}
}
