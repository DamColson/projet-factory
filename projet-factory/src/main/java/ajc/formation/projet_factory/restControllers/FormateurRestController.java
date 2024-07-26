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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.dto.request.FormateurRequest;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.dto.response.FormateurResponse;
import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.VideoProjecteur;
import ajc.formation.projet_factory.services.CompetenceService;
import ajc.formation.projet_factory.services.CompteService;
import ajc.formation.projet_factory.services.FormateurService;
import ajc.formation.projet_factory.services.OrdinateurService;
import ajc.formation.projet_factory.services.VideoProjecteurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/formateur")
@CrossOrigin("*")
@SecurityRequirement(name = "basicAuth")
public class FormateurRestController {
    @Autowired
    private FormateurService formateurService;
    @Autowired
    private CompteService compteService;
    @Autowired
    private OrdinateurService ordinateurService;
    @Autowired
    private VideoProjecteurService videoProjecteurService;
    @Autowired
    private CompetenceService competenceService;

    @GetMapping("")
    @JsonView(CustomJsonViews.FormateurWithAttributes.class)
    public List<FormateurResponse> getAll() {
        return formateurService.getAll().stream().map(formateur->new FormateurResponse(formateur)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.FormateurWithAttributes.class)
    public FormateurResponse getById(@PathVariable("id") Integer id) {
        return new FormateurResponse(formateurService.getById(id));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.FormateurWithAttributes.class)
    public FormateurResponse create(@Valid @RequestBody FormateurRequest formateurRequest, BindingResult br) {
       if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Formateur formateur = new Formateur();
        BeanUtils.copyProperties(formateurRequest, formateur);
        
        if(formateurRequest.getCompetencesId() != null){
            Set<Competence> competences = new HashSet<>();
            competences = formateurRequest.getCompetencesId().stream().map(competenceId->competenceService.getById(competenceId)).collect(Collectors.toSet()); 
            formateur.setCompetences(competences);
        }

        Compte compte = compteService.getById(formateurRequest.getCompteId());
        formateur.setCompte(compte);

        if(formateurRequest.getOrdinateurId() != null){
            Ordinateur ordinateur = ordinateurService.getById(formateurRequest.getOrdinateurId());
            formateur.setOrdinateur(ordinateur);
        }

        if(formateurRequest.getVideoProjecteurId() != null){
            VideoProjecteur videoProjecteur = videoProjecteurService.getById(formateurRequest.getVideoProjecteurId());
            formateur.setVideoProjecteur(videoProjecteur);
        }
        return new FormateurResponse(formateurService.insert(formateur));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.FormateurWithAttributes.class)
    public FormateurResponse update(@Valid @RequestBody FormateurRequest formateurRequest, BindingResult br, @PathVariable Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Formateur formateur = new Formateur();
        BeanUtils.copyProperties(formateurRequest, formateur);

        if(formateurRequest.getCompetencesId() != null){
            Set<Competence> competences = new HashSet<>();
            competences = formateurRequest.getCompetencesId().stream().map(competenceId->competenceService.getById(competenceId)).collect(Collectors.toSet()); 
            formateur.setCompetences(competences);
        }
        
        Compte compte = compteService.getById(formateurRequest.getCompteId());
        formateur.setCompte(compte);

        if(formateurRequest.getOrdinateurId() != null){
            Ordinateur ordinateur = ordinateurService.getById(formateurRequest.getOrdinateurId());
            formateur.setOrdinateur(ordinateur);
        }

        if(formateurRequest.getVideoProjecteurId() != null){
            VideoProjecteur videoProjecteur = videoProjecteurService.getById(formateurRequest.getVideoProjecteurId());
            formateur.setVideoProjecteur(videoProjecteur);
        }

        formateur.setId(id);
        return new FormateurResponse(formateurService.update(formateur));
    }

    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		formateurService.delete(formateurService.getById(id));
	}
    
}
