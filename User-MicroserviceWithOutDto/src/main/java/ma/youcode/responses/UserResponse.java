package ma.youcode.responses;

import java.util.Date;

public class UserResponse {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private boolean accepte;
//	private Date timeCreated;

//	public Date getTimeCreated() {
//		return timeCreated;
//	}
//
//	public void setTimeCreated(Date timeCreated) {
//		this.timeCreated = timeCreated;
//	}

	private String countact;

	private String addresse;

	public boolean isAccepte() {
		return accepte;
	}

	public void setAccepte(boolean accepte) {
		this.accepte = accepte;
	}

	private String role;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

}
