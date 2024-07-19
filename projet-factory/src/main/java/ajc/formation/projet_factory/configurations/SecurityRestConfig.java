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
			.requestMatchers(HttpMethod.GET).authenticated()
			.anyRequest().permitAll();
	});
	http.sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	
	http.httpBasic(Customizer.withDefaults());

	return http.build();
	}
	
}
