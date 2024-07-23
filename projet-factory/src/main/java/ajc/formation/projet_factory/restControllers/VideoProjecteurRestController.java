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

import ajc.formation.projet_factory.dto.request.VideoProjecteurRequest;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.dto.response.VideoProjecteurResponse;
import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.model.VideoProjecteur;
import ajc.formation.projet_factory.services.SalleService;
import ajc.formation.projet_factory.services.VideoProjecteurService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/videoProjecteur")
@CrossOrigin("*")
public class VideoProjecteurRestController {
    @Autowired
    private VideoProjecteurService videoProjecteurService;
    @Autowired
    private SalleService salleService;

    @GetMapping("")
    @JsonView(CustomJsonViews.VideoProjecteurWithAttributes.class)
    public List<VideoProjecteurResponse> getAll() {
        return videoProjecteurService.getAll().stream().map(videoProjecteur->new VideoProjecteurResponse(videoProjecteur)).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.VideoProjecteurWithAttributes.class)
    public VideoProjecteurResponse getById(@PathVariable("id") Integer id) {
        return new VideoProjecteurResponse(videoProjecteurService.getById(id));
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.VideoProjecteurWithAttributes.class)
    public VideoProjecteurResponse create(@Valid @RequestBody VideoProjecteurRequest videoProjecteurRequest, BindingResult br) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        VideoProjecteur videoProjecteur = new VideoProjecteur();
        BeanUtils.copyProperties(videoProjecteurRequest, videoProjecteur);

        if(videoProjecteurRequest.getSalleId() !=null){
            Salle salle = salleService.getById(videoProjecteurRequest.getSalleId());
            videoProjecteur.setSalle(salle);
        }

        return new VideoProjecteurResponse(videoProjecteurService.insert(videoProjecteur));
    }

    @PutMapping("/{id}")
    @JsonView(CustomJsonViews.VideoProjecteurWithAttributes.class)
    public VideoProjecteurResponse update(@Valid @RequestBody VideoProjecteurRequest videoProjecteurRequest, BindingResult br, @PathVariable Integer id) {
        if(br.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        VideoProjecteur videoProjecteur = new VideoProjecteur();
        BeanUtils.copyProperties(videoProjecteurRequest, videoProjecteur);
        
        if(videoProjecteurRequest.getSalleId() !=null){
            Salle salle = salleService.getById(videoProjecteurRequest.getSalleId());
            videoProjecteur.setSalle(salle);
        }
       
        videoProjecteur.setId(id);
        return new VideoProjecteurResponse(videoProjecteurService.update(videoProjecteur));
    }

    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		videoProjecteurService.delete(videoProjecteurService.getById(id));
	}
}
