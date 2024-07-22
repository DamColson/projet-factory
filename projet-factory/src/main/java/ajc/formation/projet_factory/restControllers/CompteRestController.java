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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.dto.request.CompteRequest;
import ajc.formation.projet_factory.dto.response.CompteResponse;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.model.Compte;
import ajc.formation.projet_factory.model.Role;
import ajc.formation.projet_factory.services.CompteService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/compte")
@CrossOrigin("*")
public class CompteRestController {

    @Autowired
    private CompteService compteService;

    @GetMapping("")
    @JsonView(CustomJsonViews.CompetenceWithAttributes.class)
    public List<CompteResponse> getALl() {
        return compteService.getAll().stream().map(compte->new CompteResponse(compte)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.CompetenceWithAttributes.class)
    public CompteResponse getById(@PathVariable("id") Integer id) {
        return new CompteResponse(compteService.getById(id));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.CompetenceWithAttributes.class)
    public CompteResponse create(@Valid @RequestBody CompteRequest compteRequest, BindingResult br) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Compte compte = new Compte();
        
        BeanUtils.copyProperties(compteRequest, compte,"role");
        compte.setRole(Role.valueOf(compteRequest.getRole()));
        return new CompteResponse(compteService.insert(compte));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.CompetenceWithAttributes.class)
    public CompteResponse update(@Valid @RequestBody CompteRequest compteRequest, BindingResult br, @PathVariable("id") Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Compte compte = new Compte();
        BeanUtils.copyProperties(compteRequest, compte,"role");
        compte.setRole(Role.valueOf(compteRequest.getRole()));
        compte.setId(id);
        return new CompteResponse(compteService.update(compte));
    }
    
    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		compteService.delete(compteService.getById(id));
	}
    
}
