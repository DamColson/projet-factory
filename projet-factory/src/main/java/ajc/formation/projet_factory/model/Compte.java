package ajc.formation.projet_factory.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="compte")
public class Compte implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String login;

	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy="compte")
	List<Technicien> techniciens;
	
	@OneToMany(mappedBy="compte")
	List<Formateur> formateurs;
	
	@OneToMany(mappedBy="compte")
	List<Stagiaire> stagiaires;
	
	@OneToMany(mappedBy="compte")
	List<Gestionnaire> gestionnaires;
	
	public Compte() {
	}

	public Compte(Integer id, String login, String password, Role role, List<Technicien> techniciens,
			List<Formateur> formateurs, List<Stagiaire> stagiaires, List<Gestionnaire> gestionnaires) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.techniciens = techniciens;
		this.formateurs = formateurs;
		this.stagiaires = stagiaires;
		this.gestionnaires = gestionnaires;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Technicien> getTechniciens() {
		return techniciens;
	}

	public void setTechniciens(List<Technicien> techniciens) {
		this.techniciens = techniciens;
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public List<Gestionnaire> getGestionnaires() {
		return gestionnaires;
	}

	public void setGestionnaires(List<Gestionnaire> gestionnaires) {
		this.gestionnaires = gestionnaires;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.toString()));
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
