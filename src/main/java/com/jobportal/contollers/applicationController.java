/*=================================================
* 			JobPortal Project
* 	  
*      		Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.contollers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dao.UserApplicationDAO;
import com.jobportal.pojo.UserApplication;
import com.jobportal.services.MailService;
@RestController
public class applicationController {

		@Autowired
		private UserApplicationDAO appdao;
		@Autowired
		private MailService emailService;
		//----------------------- View Application --------------------------
		@PostMapping("/applicants/application")
		public Map<String, Object> viewApplication(@RequestBody long id) {
			Map<String, Object> response = new HashMap<String, Object>();
			UserApplication ua = appdao.viewApplicants(id);
			if(ua!=null) {
				response.put("status", "success");
				response.put("application", ua);
			}else {
				response.put("status", "error");
				response.put("message", "Application not open! try again later.");
			}
			return response;
		}
		//------------------------------ Approve or Reject application ------------------
		@PutMapping("/applicants/action")
		public Map<String, String> applicationAction(@PathVariable long id, String btn)throws Exception{
			Map<String, String> response = new HashMap<>();
			if(appdao.applicationStatus(id, btn)) {
				response.put("status", "success");
				response.put("message", "Done!");
				UserApplication ua = appdao.viewApplicants(id);
				if(btn.equals("approved"))
					emailService.sendJobApplicationApproveMail(ua.getUser().getEmail(), ua.getJob().getTitle(), ua.getJob().getCompany());
				else
					emailService.sendJobApplicationRejectMail(ua.getUser().getEmail(), ua.getJob().getTitle(), ua.getJob().getCompany());
			}else {
				response.put("status", "error");
				response.put("message", "Technical issue try later!");
			}
			return response;
		}
		

}
