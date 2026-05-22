/*=================================================
* 			JobPortal Project
* 	  
*      		Git(kamlesh00-cod)
* ================================================*/
//---------- [ User Entity Class ] ----------
package com.jobportal.pojo;

import com.jobportal.enums.UserRoles;
import com.jobportal.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserRoles role;
	private String profilePicture;
	private String bio;
	@Enumerated(EnumType.STRING)
	private UserStatus status;
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(long id, String name, String email, String password, UserRoles role, String profilePicture, String bio, UserStatus status) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.role = role;
	this.profilePicture = profilePicture;
	this.bio = bio;
	this.status=status;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public UserRoles getRole() {
	return role;
}
public void setRole(UserRoles role) {
	this.role = role;
}

public String getProfilePicture() {
	return profilePicture;
}
public void setProfilePicture(String profilePicture) {
	this.profilePicture = profilePicture;
}
public String getBio() {
	return bio;
}
public void setBio(String bio) {
	this.bio = bio;
}

public UserStatus getStatus() {
	return status;
}
public void setStatus(UserStatus status) {
	this.status = status;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
			+ ", profilePicture=" + profilePicture + ", bio=" + bio + ", status=" + status + "]";
}



}
