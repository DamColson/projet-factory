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

import ajc.formation.projet_factory.dto.request.SalleRequest;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.dto.response.SalleResponse;
import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.services.SalleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/salle")
@CrossOrigin("*")
@SecurityRequirement(name = "basicAuth")
public class SalleRestController {
    @Autowired
    private SalleService salleService;


    @GetMapping("")
    @JsonView(CustomJsonViews.SalleWithAttributes.class)
    public List<SalleResponse> getAll() {
        return salleService.getAll().stream().map(salle->new SalleResponse(salle)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.SalleWithAttributes.class)
    public SalleResponse getById(@PathVariable("id") Integer id) {
        return new SalleResponse(salleService.getById(id));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.SalleWithAttributes.class)
    public SalleResponse create(@Valid @RequestBody SalleRequest salleRequest, BindingResult br) {
       if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Salle salle = new Salle();
        BeanUtils.copyProperties(salleRequest, salle);

        return new SalleResponse(salleService.insert(salle));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.SalleWithAttributes.class)
    public SalleResponse update(@Valid @RequestBody SalleRequest salleRequest, BindingResult br, @PathVariable Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Salle salle = new Salle();
        BeanUtils.copyProperties(salleRequest, salle);

        salle.setId(id);
        return new SalleResponse(salleService.update(salle));
    }

    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		salleService.delete(salleService.getById(id));
	}
}
