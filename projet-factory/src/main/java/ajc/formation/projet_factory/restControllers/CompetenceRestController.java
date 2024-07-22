package ajc.formation.projet_factory.restControllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.dto.request.CompetenceRequest;
import ajc.formation.projet_factory.dto.response.CompetenceResponse;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.model.Competence;
import ajc.formation.projet_factory.services.CompetenceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/api/Competence")
public class CompetenceRestController {
    
    @Autowired
    private CompetenceService competenceService;


    @GetMapping("")
    @JsonView(CustomJsonViews.CompetenceWithAttributes.class)
    public List<CompetenceResponse> getAll() {
        return competenceService.getAll().stream().map(competence->new CompetenceResponse(competence)).collect(Collectors.toList());
    }

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.CompetenceWithAttributes.class)
    public CompetenceResponse create(@Valid @RequestBody CompetenceRequest competenceRequest, BindingResult br) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Competence competence = new Competence();
        BeanUtils.copyProperties(competenceRequest, competence);
        return new CompetenceResponse(competenceService.insert(competence));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.CompetenceWithAttributes.class)
    public CompetenceResponse update(@Valid @RequestBody CompetenceRequest competenceRequest, BindingResult br, @PathVariable("id") Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Competence competence = new Competence();
        BeanUtils.copyProperties(competenceRequest, competence);
        competence.setId(id);
        
        return new CompetenceResponse(competenceService.update(competence));
    }
    
    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		competenceService.delete(competenceService.getById(id));
	}
}
