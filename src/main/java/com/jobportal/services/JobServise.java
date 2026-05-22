/*=================================================
* 			JobPortal Project
* 	
*     	 	git(kamlesh00-cod)
* ================================================*/
// ----- [This Class Just For Service Test All Operations Are done by DAO Class] ---------
package com.jobportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dao.JobDAO;
import com.jobportal.dao.UserApplicationDAO;
import com.jobportal.dao.UserDAO;
import com.jobportal.enums.ApplicationStatus;
import com.jobportal.pojo.Job;
import com.jobportal.pojo.User;
import com.jobportal.pojo.UserApplication;

@Service
public class JobServise {
	
	@Autowired
	private UserApplicationDAO appDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private JobDAO jDao;
	/*===================================================
	 * 		check if User apply for same job(Pending)
	 * ==================================================*/
	public boolean checkApplication(long userId, long jobId) {
		User user=userDao.searchUserById(userId);
		Job job= jDao.searchJobById(jobId);
		if(user == null || job==null) {
			return false;
		}
		UserApplication exist=appDao.searchbyUserAndJob(user, job);
		
		return exist != null && exist.getStatus() == ApplicationStatus.PENDING;
	}
}
