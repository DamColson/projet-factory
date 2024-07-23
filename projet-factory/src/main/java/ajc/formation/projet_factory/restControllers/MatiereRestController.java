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

import ajc.formation.projet_factory.dto.request.MatiereRequest;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.dto.response.MatiereResponse;
import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.model.Matiere;
import ajc.formation.projet_factory.services.CompetenceService;
import ajc.formation.projet_factory.services.MatiereService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/matiere")
@CrossOrigin("*")
public class MatiereRestController {
    @Autowired
    private MatiereService matiereService;
    @Autowired
    private CompetenceService competenceService;

    @GetMapping("")
    @JsonView(CustomJsonViews.MatiereWithAttributes.class)
    public List<MatiereResponse> getAll() {
        return matiereService.getAll().stream().map(matiere->new MatiereResponse(matiere)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.MatiereWithAttributes.class)
    public MatiereResponse getById(@PathVariable("id") Integer id) {
        return new MatiereResponse(matiereService.getById(id));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.MatiereWithAttributes.class)
    public MatiereResponse create(@Valid @RequestBody MatiereRequest matiereRequest, BindingResult br) {
       if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Matiere matiere = new Matiere();
        BeanUtils.copyProperties(matiereRequest, matiere);

        if(matiereRequest.getCompetencesId() != null){
            Set<Competence> competences = new HashSet<>();
            competences = matiereRequest.getCompetencesId().stream().map(competenceId-> competenceService.getById(competenceId)).collect(Collectors.toSet());
            matiere.setCompetences(competences);
        }

        return new MatiereResponse(matiereService.insert(matiere));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.MatiereWithAttributes.class)
    public MatiereResponse update(@Valid @RequestBody MatiereRequest matiereRequest, BindingResult br, @PathVariable Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Matiere matiere = new Matiere();
        BeanUtils.copyProperties(matiereRequest, matiere);

        if(matiereRequest.getCompetencesId() != null){
            Set<Competence> competences = new HashSet<>();
            competences = matiereRequest.getCompetencesId().stream().map(competenceId-> competenceService.getById(competenceId)).collect(Collectors.toSet());
            matiere.setCompetences(competences);
        }
        
        matiere.setId(id);
        return new MatiereResponse(matiereService.update(matiere));
    }

    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		matiereService.delete(matiereService.getById(id));
	}
}
