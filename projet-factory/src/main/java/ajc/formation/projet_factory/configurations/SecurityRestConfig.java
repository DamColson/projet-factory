package ajc.formation.projet_factory.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(type = SecuritySchemeType.HTTP,name="basicAuth",scheme = "basic")
@Configuration
public class SecurityRestConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests(auth->{
			auth.requestMatchers("/swagger-ui/**","/swagger-ui.html","/v3/**").permitAll()
			.requestMatchers(HttpMethod.OPTIONS).permitAll()
			.requestMatchers(HttpMethod.GET,"/api/auth").authenticated()
			//Technicien
			.requestMatchers(HttpMethod.GET,"/api/ordinateur","/api/ordinateur/*","/api/videoProjecteur","/api/videoProjecteur/*","/api/salle","/api/salle/*").hasAnyAuthority("ROLE_GESTIONNAIRE","ROLE_TECHNICIEN","ROLE_ADMIN")
			.requestMatchers(HttpMethod.POST,"/api/ordinateur","/api/videoProjecteur").hasAnyAuthority("ROLE_TECHNICIEN","ROLE_ADMIN")
			.requestMatchers(HttpMethod.PUT,"/api/ordinateur/*","/api/videoProjecteur/*").hasAnyAuthority("ROLE_TECHNICIEN","ROLE_ADMIN")
			.requestMatchers(HttpMethod.DELETE,"/api/ordinateur/*","/api/videoProjecteur/*").hasAnyAuthority("ROLE_TECHNICIEN","ROLE_ADMIN")
			//gestionnaire
			.requestMatchers(HttpMethod.POST,"/api/matiere","/api/bloc","/api/competence","/api/formation","/api/salle").hasAnyAuthority("ROLE_GESTIONNAIRE","ROLE_ADMIN")
			.requestMatchers(HttpMethod.PUT,"/api/stagiaire/*","/api/matiere/*","/api/bloc/*","/api/competence/*","/api/formation/*","/api/salle/*").hasAnyAuthority("ROLE_GESTIONNAIRE","ROLE_ADMIN")
			.requestMatchers(HttpMethod.DELETE,"/api/matiere/*","/api/bloc/*","/api/competence/*","/api/formation/*","/api/salle/*").hasAnyAuthority("ROLE_GESTIONNAIRE","ROLE_ADMIN")
			//stagiaire
			.requestMatchers(HttpMethod.GET,"/api/formation/*","/api/bloc/*","/api/stagiaire/*").hasAnyAuthority("ROLE_ADMIN","ROLE_STAGIAIRE","ROLE_FORMATEUR","ROLE_GESTIONNAIRE")
			//formateur
			.requestMatchers(HttpMethod.GET,"/api/formateur","/api/formateur/*").hasAnyAuthority("ROLE_FORMATEUR","ROLE_GESTIONNAIRE","ROLE_ADMIN")
			.requestMatchers(HttpMethod.GET,"/api/*","/api/*/*").hasAnyAuthority("ROLE_GESTIONNAIRE","ROLE_ADMIN")
			.anyRequest().hasAnyAuthority("ROLE_ADMIN")
			;
	});
	http.sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	
	http.httpBasic(Customizer.withDefaults());

	return http.build();
	}
	
}
