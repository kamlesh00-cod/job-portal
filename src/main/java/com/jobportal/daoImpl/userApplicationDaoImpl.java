/*=================================================
* 			JobPortal Project
* 	 
*     	 	Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.daoImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.dao.UserApplicationDAO;
import com.jobportal.enums.ApplicationStatus;
import com.jobportal.pojo.Job;
import com.jobportal.pojo.User;
import com.jobportal.pojo.UserApplication;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserApplicatonRepository;
import com.jobportal.repository.UserRepository;
@Component
public class userApplicationDaoImpl implements UserApplicationDAO{

	@Autowired
	private UserApplicatonRepository appRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JobRepository jobRepo;
	
	/*=================================================
	 * 			 View Submitted Applications  
	 * ================================================*/
	@Override
	public UserApplication viewApplicants(long id) {
		try {
			Optional<UserApplication> op = appRepo.findById(id);
			if(op.isPresent()) {
				return op.get();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*=================================================
	 * 			User Submitted Application
	 * ================================================*/
	@Override
	public boolean saveUserApplication(UserApplication app,long jobId,long userId,MultipartFile file) {
		try {
			Optional<User> uop = userRepo.findById(userId); 
			Optional<Job> jop = jobRepo.findById(jobId);
			if(uop.isEmpty()&&jop.isEmpty()) {
				return false;
			}
			User user = uop.get();
			Job job = jop.get();
			
			String fileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
			//Upload Path
			Path uploadPath = Paths.get("D:\\coding folder\\j2ee\\jobportal\\src\\main\\webapp\\resume_link");
			Path filePath = uploadPath.resolve(fileName);
			
			if(appRepo.existsById(app.getId())) {
				return false;
			}else {
				app.setUser(user);
				app.setJob(job);
				app.setStatus(ApplicationStatus.PENDING);
				app.setResumeLink(fileName);
				appRepo.save(app);
				Files.copy(file.getInputStream() ,filePath,StandardCopyOption.REPLACE_EXISTING);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*=================================================================
	 * 			(Approved/Rejected)Change Application Status by Admin
	 * ===============================================================*/
	@Override
	public boolean applicationStatus(long id , String status) {
		try {
			Optional<UserApplication> op = appRepo.findById(id);
			
			if(op.isPresent()) {
				UserApplication uapp= op.get();
				ApplicationStatus appStatus= ApplicationStatus.valueOf(status.toUpperCase());
				uapp.setStatus(appStatus);
				appRepo.save(uapp);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*=================================================
	 * 			List of User Submitted Applications
	 * ================================================*/
	@Override
	public List<UserApplication> getAllApplications() {
		try {
			List<UserApplication> lst = (List<UserApplication>)appRepo.findAll();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	/*=========================================================
	 * 			Count Total Applications or Pending/Selected
	 * ========================================================*/
	@Override
	public Map<String, Integer> counterUserApplication() {
		Map<String, Integer> count = new HashMap<String, Integer>();
		List<UserApplication> lst = (List<UserApplication>)appRepo.findAll();
		if(lst==null||lst.isEmpty()) {
			count.put("totalapplication", 0);
			count.put("pendings", 0);
			count.put("selected", 0);
			return count;
		}
		int totalApplications = lst.size();
		int pendings=0,selected=0;
		
		for(UserApplication app : lst) {
			if(app.getStatus()==ApplicationStatus.PENDING) {
				pendings++;
			}
			if(app.getStatus()==ApplicationStatus.APPROVED) {
				selected++;
			}
		}
		count.put("totalapplication", totalApplications);
		count.put("pendings", pendings);
		count.put("selected", selected);
		return count;
	}

	/*=================================================
	 * 			(Search)Find If User Already Applied For a Job
	 * ================================================*/
	@Override
	public UserApplication searchbyUserAndJob(User user, Job job) {
		try {
			Optional<UserApplication> op = appRepo.findByUserAndJob(user, job);
			if(op.isPresent()) {
				UserApplication existUser= op.get();
				
				return existUser;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	/*=================================================
	 * 			(Search)Find User Applications by Name 
	 * ================================================*/
	@Override
	public List<UserApplication> searchByUserName(String name) {
		try {
			List<UserApplication> search = appRepo.searchUserApplication(name);
			if(search!=null ) {
				return search;	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//return Collections.emptyList();
		}
		return Collections.emptyList();
	}
	
	
	
	
}
