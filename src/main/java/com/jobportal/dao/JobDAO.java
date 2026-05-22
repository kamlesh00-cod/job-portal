/*=================================================
* 			JobPortal Project
* 	  
*      		Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jobportal.pojo.Job;

public interface JobDAO {

//manage jobs
	//add jobs
	boolean saveJob(Job j);
	//Update job
	boolean updateJob(Job j);
	//delete job
	boolean deleteJob(long jobId);
	//view all job
	List<Job> getAllJobs();
	Page<Job> getAllJobs(int page,int size);
	//search job
	Job searchJobById(long id);
	List<Job> searchJobByName(String jobName);

	
}
