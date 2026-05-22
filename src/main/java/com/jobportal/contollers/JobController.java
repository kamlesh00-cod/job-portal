/*=================================================
* 			JobPortal Project
* 	  
*      		Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.contollers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.jobportal.dao.UserApplicationDAO;
import com.jobportal.pojo.UserApplication;
import com.jobportal.services.JobServise;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class JobController {
	

	
	@Autowired
	private UserApplicationDAO appdao;
	@Autowired
	private JobServise jobSer;
/*==================================================
 * 			User apply jobs
 * ==================================================*/
	@PostMapping("/jobs/apply")
	public ResponseEntity<Map<String, String>> jobApply(@ModelAttribute UserApplication application,@RequestParam("jobId")long jobid,@RequestParam("userId")long userid, @RequestParam("resume") MultipartFile file) throws Exception{
		Map<String, String> response= new HashMap<>();
		long maxSize = 10 * 1024 * 1024; // 10 MB
		
		
		if(file==null||file.isEmpty()) {
			 response.put("status", "error");
		     response.put("message", "Please upload a file!");
			return ResponseEntity.badRequest().body(response);
		}
		if (file.getSize() > maxSize) {
			 response.put("status", "error");
			 response.put("message", "File size must be less than 10MB");
		    return ResponseEntity.badRequest().body(response);
		}
		if(jobSer.checkApplication(userid, jobid)) {
			response.put("status", "success");
			response.put("message", "You are already applied this job!");
			response.put("redirectURL", "/user/jobs");
			return ResponseEntity.ok(response);
		}
		if(appdao.saveUserApplication(application,jobid,userid, file)) {
			response.put("status", "success");
			response.put("message", "You applied successfully!");
			response.put("redirectURL", "/user/jobs");
			return ResponseEntity.ok(response);
		}else {
			response.put("status", "error");
			response.put("message","Error on apply. Please try later!");
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
}
