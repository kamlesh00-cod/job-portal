/*=================================================
* 			JobPortal Project
* 	  
*      		Git(kamlesh00-cod)
* ================================================*/
//---------- [ User Application Entity Class ] ----------
package com.jobportal.pojo;

import com.jobportal.enums.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="user_application")
public class UserApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="job_id")
	private Job job;
	private String resumeLink;
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;
	private String coverLetter;
	public UserApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserApplication(long id, User user, Job job, String resumeLink, ApplicationStatus status,
			String coverLetter) {
		super();
		this.id = id;
		this.user = user;
		this.job = job;
		this.resumeLink = resumeLink;
		this.status = status;
		this.coverLetter = coverLetter;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getResumeLink() {
		return resumeLink;
	}
	public void setResumeLink(String resumeLink) {
		this.resumeLink = resumeLink;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	
	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	@Override
	public String toString() {
		return "UserApplication [id=" + id + ", user=" + user + ", job=" + job + ", resumeLink=" + resumeLink
				+ ", status=" + status + ", coverLetter=" + coverLetter + "]";
	}

	
	
}
