package ajc.formation.projet_factory.restControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.dto.request.BlocRequest;
import ajc.formation.projet_factory.dto.response.BlocResponse;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.model.Bloc;
import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.Matiere;
import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.services.BlocService;
import ajc.formation.projet_factory.services.FormateurService;
import ajc.formation.projet_factory.services.MatiereService;
import ajc.formation.projet_factory.services.SalleService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/bloc")
@CrossOrigin("*")
public class BlocRestController {
    
    @Autowired
    private BlocService blocService;
    @Autowired
    private FormateurService formateurService;
    @Autowired
    private MatiereService matiereService;
    @Autowired
    private SalleService salleService;

    @GetMapping("")
    @JsonView(CustomJsonViews.BlocWithAttributes.class)
    public List<BlocResponse> getAll() {
        return blocService.getAll().stream().map(bloc->new BlocResponse(bloc)).collect(Collectors.toList());
    }

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.BlocWithAttributes.class)
    public BlocResponse create(@Valid @RequestBody BlocRequest blocRequest, BindingResult br) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Bloc bloc = new Bloc();
        BeanUtils.copyProperties(blocRequest, bloc);
        
        Formateur formateur = formateurService.getById(blocRequest.getFormateurId());
        bloc.setFormateur(formateur);

        Matiere matiere = matiereService.getById(blocRequest.getMatiereId());
        bloc.setMatiere(matiere);

        Salle salle = salleService.getById(blocRequest.getSalleId());
        bloc.setSalle(salle);
        
        return new BlocResponse(blocService.insert(bloc));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.BlocWithAttributes.class)
    public BlocResponse update(@Valid @RequestBody BlocRequest blocRequest, BindingResult br, @PathVariable("id") Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Bloc bloc = new Bloc();
        BeanUtils.copyProperties(blocRequest, bloc);
         
        Formateur formateur = formateurService.getById(blocRequest.getFormateurId());
        bloc.setFormateur(formateur);

        Matiere matiere = matiereService.getById(blocRequest.getMatiereId());
        bloc.setMatiere(matiere);

        Salle salle = salleService.getById(blocRequest.getSalleId());
        bloc.setSalle(salle);

        bloc.setId(id);
        
        return new BlocResponse(blocService.update(bloc));
    }
    
    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		blocService.delete(blocService.getById(id));
	}
}
