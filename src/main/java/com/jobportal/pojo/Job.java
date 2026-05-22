
/*=================================================
* 			JobPortal Project
* 	
*     	 	Git(kamlesh00-cod)
* ================================================*/
//---------- [ Job Entity Class ] ----------
package com.jobportal.pojo;

import com.jobportal.enums.JobTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="job")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String description;
	private String location;
	private String company;
	@Enumerated(EnumType.STRING)
	private JobTypes jobType;
public Job() {
	super();
	// TODO Auto-generated constructor stub
}

public Job(long id, String title, String description, String location, String company, JobTypes jobType) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.location = location;
	this.company = company;
	this.jobType = jobType;
}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}

public JobTypes getJobType() {
	return jobType;
}

public void setJobType(JobTypes jobType) {
	this.jobType = jobType;
}

@Override
public String toString() {
	return "Job [id=" + id + ", title=" + title + ", description=" + description + ", location=" + location
			+ ", company=" + company + ", jobType=" + jobType + "]";
}

}
