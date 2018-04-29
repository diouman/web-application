package model;

import javax.persistence.*;
import javax.persistence.Id;



@Entity
@Table(name="logins")
public class Login {
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	
	public Login() {
		
	}

	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	@Id
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
