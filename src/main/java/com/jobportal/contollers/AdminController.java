
/*=================================================
	 * 			JobPortal Project
	 * 	
	 *      	Git(kamlesh00-cod)
	 * ================================================*/

/*===========================================
    		Only for Admin (RestAPI)
=============================================== */
package com.jobportal.contollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dao.JobDAO;
import com.jobportal.dao.UserDAO;
import com.jobportal.dao.UserApplicationDAO;
import com.jobportal.enums.ApplicationStatus;
import com.jobportal.enums.UserStatus;
import com.jobportal.pojo.Job;
import com.jobportal.pojo.User;
import com.jobportal.pojo.UserApplication;
import com.jobportal.services.MailService;
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private JobDAO jdao;
	@Autowired
	private UserApplicationDAO appdao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private MailService emailService;
	
	/*===================================================================
	 * 				Post jobs
	 * ==================================================================*/
	@PostMapping("/jobs/postjob")
	public Map<String, String> postJob( Job job){
		Map<String, String> response = new HashMap<String, String>();
		if(jdao.saveJob(job)) {
			response.put("status", "success");
			response.put("message", "Job posted!");
		}else {
			response.put("status", "error");
			response.put("message", "Failed to post job!");
		}
		return response;
	}
	
	/*===================================================================
	 * 				Update Posted Jobs
	 * ====================================================================*/
	@PostMapping("/jobs/update")
	public Map<String ,String> updatejobs( Job job ){
		Map<String,String> response=new HashMap<String, String>();
		if(jdao.updateJob(job)) {
			response.put("status", "success");
			response.put("message", "Job updated!");
		}else {
			response.put("status", "error");
			response.put("message", "Job not saved. try later!");
		}
		return response;
	}
	
	/*===================================================================
	 * 					Delete Jobs
	 * ==================================================================*/
	@DeleteMapping("/jobs/delete/{id}")
	public Map<String ,String> deletejobs(@PathVariable long id ){
		Map<String,String> response=new HashMap<String, String>();
		if(jdao.deleteJob(id)) {
			response.put("status", "success");
			response.put("message", "Job deleted!");
		}else {
			response.put("status", "error");
			response.put("message", "Job not deleted. try later!");
		}
		return response;
	}
	
	/*===================================================================
	 * 				Search Jobs By Ids
	 * ==================================================================*/
	@GetMapping("/jobs/search/{id}")
	public Map<String ,Object> searchjobs(@PathVariable long id ){
		Map<String,Object> response=new HashMap<>();
		Job job= jdao.searchJobById(id);
		if(job!=null) {
			response.put("status", "success");
			response.put("message", "Job found!");
			response.put("job", job);
		}else {
			response.put("status", "error");
			response.put("message", "Job not found. try later!");
		}
		return response;
	}
	
	/*====================================================================
	 * 					Approve Or Reject User Applications
	 * ===================================================================*/
	@PostMapping("/applicants/select")
	public Map<String ,String> approveOrRejectApplicantions(@RequestParam() long id, @RequestParam()String status )throws Exception{
		Map<String,String> response=new HashMap<String, String>();
		
		if(appdao.applicationStatus(id, status)) {
			response.put("status", "success");
			response.put("message", "You "+status+" applicant!");
			UserApplication ua=appdao.viewApplicants(id);
			if(ua.getStatus()==ApplicationStatus.APPROVED)
				emailService.sendJobApplicationApproveMail(ua.getUser().getEmail(), ua.getJob().getTitle(), ua.getJob().getCompany());
			else
				emailService.sendJobApplicationRejectMail(ua.getUser().getEmail(), ua.getJob().getTitle(), ua.getJob().getCompany());
		}else {
			response.put("status", "error");
			response.put("message", "Con't connect properly. try later!");
		}
		return response;
	}
	
	/*==================================================================
	 * 					Delete Users
	 * =================================================================*/
	@DeleteMapping("/manageusers/delete/{id}")
	public Map<String ,String> deleteUser(@PathVariable long id )throws Exception{
		Map<String,String> response=new HashMap<String, String>();
		User user= userdao.searchUserById(id);
		if (user == null) {

	        response.put("status", "error");
	        response.put("message", "User not found!");

	        return response;
	    }
		if(userdao.deleteUser(id)) {
			response.put("status", "success");
			response.put("message", "User deleted!");
			
			emailService.sendUserDeleteMail(user.getEmail(), user.getName());
		}else {
			response.put("status", "error");
			response.put("message", "User not deleted. try later!");
		}
		return response;
	}
	/*================================================================
	 * 					Active Or Block Users
	 * ===============================================================*/
	@PutMapping("/manageusers/updatestatus/{id}")
	public Map<String ,String> updateUserStatus(@PathVariable long id )throws Exception{
		Map<String,String> response=new HashMap<String, String>();
		if(userdao.userStatus(id)) {
			response.put("status", "success");
			response.put("message", "User status has updated!");
			User user= userdao.searchUserById(id);
			if(user.getStatus()==UserStatus.ACTIVE)
				emailService.sendUserActivebMail(user.getEmail(), user.getName());
			else
				emailService.sendUserBlockMail(user.getEmail(), user.getName());
		}else {
			response.put("status", "error");
			response.put("message", "User not updated. try later!");
		}
		return response;
	}
	
	@PostMapping("/searchuser")
	public ResponseEntity<Map<String, Object>> searchUser(@RequestParam("name")String userName){
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> userSearch = userdao.searchByName(userName);
		if(userSearch.isEmpty()) {
			response.put("status", "error");
			response.put("message", "Search Not Found!");
			return ResponseEntity.ok(response);
		}else {
			response.put("status", "success");
			response.put("message", "Search Found!");
			response.put("searchResult", userSearch);
			return ResponseEntity.ok(response);
		}
	}
}
