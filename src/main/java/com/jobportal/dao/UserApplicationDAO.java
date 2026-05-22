/*=================================================
* 			JobPortal Project
* 	  
*      		Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jobportal.pojo.Job;
import com.jobportal.pojo.User;
import com.jobportal.pojo.UserApplication;

public interface UserApplicationDAO {
	
		//view user application
		UserApplication viewApplicants(long id);
		//manage application
		boolean saveUserApplication(UserApplication app,long jobId,long userId,MultipartFile file);
		boolean applicationStatus(long id,String status);
		//all applications
		List<UserApplication> getAllApplications();
		//count of Applications
		Map<String, Integer> counterUserApplication();
		//search by user or job
		UserApplication searchbyUserAndJob(User user,Job job);
		List<UserApplication> searchByUserName(String name);
	}

