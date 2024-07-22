package ajc.formation.projet_factory.restControllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.dto.response.CompteResponse;
import ajc.formation.projet_factory.dto.response.CustomJsonViews;
import ajc.formation.projet_factory.model.Compte;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthRestController {

	@JsonView(CustomJsonViews.CompteWithPerson.class)
	@GetMapping("")
	public CompteResponse auth(@AuthenticationPrincipal Compte compte) {
		return new CompteResponse(compte);
	}
	
}
