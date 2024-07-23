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

import ajc.formation.projet_factory.dto.request.StagiaireRequest;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.dto.response.StagiaireResponse;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Formation;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Stagiaire;
import ajc.formation.projet_factory.services.CompteService;
import ajc.formation.projet_factory.services.FormationService;
import ajc.formation.projet_factory.services.OrdinateurService;
import ajc.formation.projet_factory.services.StagiaireService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stagiaire")
@CrossOrigin("*")
public class StagiaireRestController {
    @Autowired
    private StagiaireService stagiaireService;
    @Autowired
    private CompteService compteService;
    @Autowired
    private OrdinateurService ordinateurService;
    @Autowired
    private FormationService formationService;

    @GetMapping("")
    @JsonView(CustomJsonViews.StagiaireWithAttributes.class)
    public List<StagiaireResponse> getAll() {
        return stagiaireService.getAll().stream().map(stagiaire->new StagiaireResponse(stagiaire)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.StagiaireWithAttributes.class)
    public StagiaireResponse getById(@PathVariable("id") Integer id) {
        return new StagiaireResponse(stagiaireService.getById(id));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.StagiaireWithAttributes.class)
    public StagiaireResponse create(@Valid @RequestBody StagiaireRequest stagiaireRequest, BindingResult br) {
       if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Stagiaire stagiaire = new Stagiaire();
        BeanUtils.copyProperties(stagiaireRequest, stagiaire);

        Compte compte = compteService.getById(stagiaireRequest.getCompteId());
        stagiaire.setCompte(compte);

        if(stagiaireRequest.getOrdinateurId() != null){
            Ordinateur ordinateur = ordinateurService.getById(stagiaireRequest.getOrdinateurId());
            stagiaire.setOrdinateur(ordinateur);
        }

        if(stagiaireRequest.getFormationId() != null){
            Formation formation = formationService.getById(stagiaireRequest.getFormationId());
            stagiaire.setFormation(formation);
        }

        return new StagiaireResponse(stagiaireService.insert(stagiaire));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.StagiaireWithAttributes.class)
    public StagiaireResponse update(@Valid @RequestBody StagiaireRequest stagiaireRequest, BindingResult br, @PathVariable Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Stagiaire stagiaire = new Stagiaire();
        BeanUtils.copyProperties(stagiaireRequest, stagiaire);

        Compte compte = compteService.getById(stagiaireRequest.getCompteId());
        stagiaire.setCompte(compte);

        if(stagiaireRequest.getOrdinateurId() != null){
            Ordinateur ordinateur = ordinateurService.getById(stagiaireRequest.getOrdinateurId());
            stagiaire.setOrdinateur(ordinateur);
        }

        if(stagiaireRequest.getFormationId() != null){
            Formation formation = formationService.getById(stagiaireRequest.getFormationId());
            stagiaire.setFormation(formation);
        }

        stagiaire.setId(id);
        return new StagiaireResponse(stagiaireService.update(stagiaire));
    }

    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		stagiaireService.delete(stagiaireService.getById(id));
	}
}
