package ma.youcode.VO;

import java.util.Date;

public class User {
	private String username;
	private String email;
	private String userId;
	private String adresse;
	private Date time;
	private String contact;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public User(String username, String email, String userId, String adresse, Date time, String contact) {
		super();
		this.username = username;
		this.email = email;

		this.userId = userId;
		this.adresse = adresse;
		this.time = time;
		this.contact = contact;
	}

	public User() {
		super();
	}

}