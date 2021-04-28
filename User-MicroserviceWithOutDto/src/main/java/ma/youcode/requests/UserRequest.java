package ma.youcode.requests;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {

	@NotNull(message = "Ce champ ne doit etre null !")
	private String firstName;

	@NotNull(message = "Ce champ ne doit etre null !")
	@Size(min = 3, message = "Ce champ doit avoir au moins 3 Caracteres !")
	private String lastName;

	@NotNull(message = "Ce champ ne doit etre null !")
	@Email(message = "ce champ doit respecter le format email !")
	private String email;

	@NotNull(message = "Ce champ ne doit etre null !")
	@Size(min = 8, message = "mot de passe doit avoir au moins 8 caracteres !")
	@Size(max = 12, message = "mot de passe doit avoir au max 12 caracteres !")
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "ce mot de passe doit avoir des lettres en Maj et Minsc et numero")
	private String password;

	@NotNull(message = "Ce champ ne doit etre null !")
	@Size(max = 14, message = "Ce champ doit avoir au moins 3 Caracteres !")
	private String countact;

	@NotNull(message = "Ce champ ne doit etre null !")
	private String addresse;

	@NotNull(message = "Ce champ ne doit etre null !")
	@Size(min = 4, message = "Ce champ doit avoir au moins 3 Caracteres !")
	private String role;

	private Date time;

//	@NotNull
//	private Date timeCreated;

//	@NotNull
//	private boolean accepte = true;

//	public void setAccepte(boolean accepte) {
//		this.accepte = accepte;
//	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCountact() {
		return countact;
	}

	public void setCountact(String countact) {
		this.countact = countact;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
//
//	public Date getTimeCreated() {
//		return timeCreated;
//	}
//
//	public void setTimeCreated(Date timeCreated) {
//		this.timeCreated = timeCreated;
//	}

//	public boolean isAccepte() {
//		return accepte;
//	}

}
