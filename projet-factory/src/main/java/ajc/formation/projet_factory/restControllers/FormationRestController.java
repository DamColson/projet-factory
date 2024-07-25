package ajc.formation.projet_factory.restControllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import ajc.formation.projet_factory.dto.request.FormationRequest;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.dto.response.FormationResponse;
import ajc.formation.projet_factory.model.Bloc;
import ajc.formation.projet_factory.model.Formation;
import ajc.formation.projet_factory.model.Gestionnaire;
import ajc.formation.projet_factory.model.Stagiaire;
import ajc.formation.projet_factory.services.BlocService;
import ajc.formation.projet_factory.services.FormationService;
import ajc.formation.projet_factory.services.GestionnaireService;
import ajc.formation.projet_factory.services.StagiaireService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/formation")
@CrossOrigin("*")
public class FormationRestController {
    
    @Autowired
    private FormationService formationService;
    @Autowired
    private GestionnaireService gestionnaireService;
    @Autowired
    private StagiaireService stagiaireService;
    @Autowired
    private BlocService blocService;

    @GetMapping("")
    @JsonView(CustomJsonViews.FormationWithAttributes.class)
    public List<FormationResponse> getAll() {
        return formationService.getAll().stream().map(formation->new FormationResponse(formation)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.FormationWithAttributes.class)
    public FormationResponse getById(@PathVariable("id") Integer id) {
        return new FormationResponse(formationService.getById(id));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.FormationWithAttributes.class)
    public FormationResponse create(@Valid @RequestBody FormationRequest formationRequest, BindingResult br) {
       if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Formation formation = new Formation();
        BeanUtils.copyProperties(formationRequest, formation);

        if(formationRequest.getStagiairesId() != null){
            Set<Stagiaire> stagiaires = new HashSet<>();
            stagiaires = formationRequest.getStagiairesId().stream().map(stagiaireId->stagiaireService.getById(stagiaireId)).collect(Collectors.toSet());
            formation.setStagiaires(stagiaires);
        }

        if(formationRequest.getBlocsId() != null){
            Set<Bloc> blocs = new HashSet<>();
            blocs = formationRequest.getBlocsId().stream().map(blocId->blocService.getById(blocId)).collect(Collectors.toSet());
            formation.setBlocs(blocs);
        }

        if(formationRequest.getGestionnaireId() != null){
            Gestionnaire gestionnaire = gestionnaireService.getById(formationRequest.getGestionnaireId());
            formation.setGestionnaire(gestionnaire);
        }

        return new FormationResponse(formationService.insert(formation));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.FormationWithAttributes.class)
    public FormationResponse update(@Valid @RequestBody FormationRequest formationRequest, BindingResult br, @PathVariable Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Formation formation = new Formation();
        Set<Stagiaire> stagiaires = new HashSet<>();
        BeanUtils.copyProperties(formationRequest, formation);

        if(formationRequest.getStagiairesId() != null){      
            stagiaires = formationRequest.getStagiairesId().stream().map(stagiaireId->stagiaireService.getById(stagiaireId)).collect(Collectors.toSet());
            formation.setStagiaires(stagiaires);
        }
        if(formationRequest.getBlocsId() != null){
            Set<Bloc> blocs = new HashSet<>();
            blocs = formationRequest.getBlocsId().stream().map(blocId->blocService.getById(blocId)).collect(Collectors.toSet());
            formation.setBlocs(blocs);
        }
        if(formationRequest.getGestionnaireId() != null){
            Gestionnaire gestionnaire = gestionnaireService.getById(formationRequest.getGestionnaireId());
            formation.setGestionnaire(gestionnaire);
        }
        
        formation.setId(id);
          
        stagiaires = stagiaires.stream().peek(stagiaire->{
        	stagiaire.setFormation(formation);       	
        	stagiaireService.update(stagiaire);
        }).collect(Collectors.toSet());
        
        return new FormationResponse(formationService.update(formation));
    }

    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		formationService.delete(formationService.getById(id));
	}
}
