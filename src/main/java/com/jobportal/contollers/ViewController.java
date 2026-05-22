
/*=================================================
* 			JobPortal Project
* 	 
*      		Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.contollers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobportal.dao.JobDAO;
import com.jobportal.dao.UserDAO;
import com.jobportal.dao.UserApplicationDAO;
import com.jobportal.enums.ApplicationStatus;
import com.jobportal.pojo.Job;
import com.jobportal.pojo.User;
import com.jobportal.pojo.UserApplication;

/*==========================================================
 * 	All pages mapping here (Views with Model-attributes)
 * =======================================================*/
@Controller

public class ViewController {
	@Autowired
	private JobDAO jdao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private UserApplicationDAO appdao;
	
/*====================================================
* 			Mapping for all 
* ===================================================*/

//	---------------	Login page ---------------------
@GetMapping("/login")
public String loginPage() {
    return "login";
}
//	-------------------- Registration page --------------------
@GetMapping("/register")
public String regPage() {
    return "registration";
}
//	-------------------- logout -------------------
@GetMapping("/logout")
public String logoutPage() {
    return "login";
}

// --------------------	Welcome page----------------
@GetMapping("/home")
public String home(Principal principal, Model model) {
	if(principal==null) {			
		return "home";
	}else {
		String email=principal.getName();
		User user = userdao.findByuserEmail(email);
		model.addAttribute("user",user);
		return "home";
	}
}
@GetMapping("/forgot")
public String resetLoginPage() {
    return "forgotpassword";
}
/*=================================
 * 		admin mappings
 * ================================*/
/*	----------------||	Admin dash-board ||--------------- */
@GetMapping("/admin/admindashboard")
public String adminPage(Principal principal,Model model) {
	if(principal==null) {
		return "login";
	}
	Map<String, Integer> counter =new HashMap<String, Integer>();
	List<Job> jlst= jdao.getAllJobs();
	List<User> user = userdao.getAllUsers();
	List<UserApplication> applst= appdao.getAllApplications();
	 // Jobs
    if (jlst == null || jlst.isEmpty()) {
        counter.put("jobs", 0);
        model.addAttribute("msg", "There are no jobs available.");
    } else {
        counter.put("jobs", jlst.size());
        model.addAttribute("joblst", jlst);
    }
    // Users
    if (user == null || user.isEmpty()) {
        counter.put("users", 0);
    } else {
        counter.put("users", user.size());
    }
    // Applications
    if (applst == null || applst.isEmpty()) {
        counter.put("applications", 0);
    } else {
        counter.put("applications", applst.size());
    }
	
	model.addAttribute("counts",counter);
    return "admindashboard";
}

/*	---------------||	All User ||----------*/
@GetMapping("/admin/manageusers")
public String usersPage(Principal principal,Model model) {
	if(principal==null) {
		return "login";
	}
	model.addAttribute("users",userdao.getAllUsers());
    return "manageusers";
}

/*	-------||	All application	||----------- */
@GetMapping("/admin/applicants")
public String appPage(Principal principal,Model model) {
	if(principal==null) {
		return "login";
	}
	List<UserApplication> lst = appdao.getAllApplications();
	
	if(lst==null||lst.isEmpty()) {
		model.addAttribute("msg","There is no applicantion.");
		model.addAttribute("counts",appdao.counterUserApplication());
	}else {
		model.addAttribute("applicants",lst);
		model.addAttribute("counts",appdao.counterUserApplication());
	}
    return "applicants";
}

/*	----------||	Post new jobs or see all job ||-------------	*/
@GetMapping("/admin/form")
public String jobsPage(Principal principal,Model model) {
	if(principal==null) {
		return "login";
	}
	model.addAttribute("joblist",jdao.getAllJobs());
    return "jobform";
}

/*	----------||	Update job ||----------------	*/
@GetMapping("/admin/jobform/edit")
public String updateJobPage(@RequestParam("id")long jobId,Principal principal, Model model) {
	if(principal==null) {
		return "login";
	}
	Job job = jdao.searchJobById(jobId);
	model.addAttribute("job",job);
    return "jobform";
}
/*========================================
 * 		Admin can see user profile
 * =======================================*/
@GetMapping("/admin/userdetail")
public String userDetailsPage(@RequestParam String email,@RequestParam("rsm")String fileName,Principal principal, Model model) {
	Map<String, Integer> userAppCount =new HashMap<String, Integer>();
	if(principal==null) {
		return "login";
	}
	User userInfo = userdao.findByuserEmail(email);
	int applications = 0;
	int interviews= 0;
	if(userInfo!=null) {
		List<UserApplication> lst = userdao.getAlluserApplications(userInfo);
		if(lst != null && !lst.isEmpty()) {
			
	    applications = lst.size();
		
		for(UserApplication app: lst) {
			if(app.getStatus()==ApplicationStatus.APPROVED) {
				interviews++;
			}
		}
		}
		
		model.addAttribute("user",userInfo);
		model.addAttribute("userResume",fileName);
	}
	userAppCount.put("applications", applications);
	userAppCount.put("interviews", interviews);
	model.addAttribute("userAppCount",userAppCount);	
	//model.addAttribute("joblist",jdao.getAllJobs());
    return "userdetail";
}

/*======================================
 * 		User mappings here
 * =====================================*/
/*	-------------||	jobs with pages(every page have 5 jobs)	||-----------	*/
@GetMapping("/user/jobs")
public String jobPage(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "5")int size,Model model,Principal principal) {
	Page<Job> jobs=jdao.getAllJobs(page,size);
	if(principal==null) {
		model.addAttribute("user",null);
	}else {
		String email=principal.getName();
		User user = userdao.findByuserEmail(email);
		model.addAttribute("user",user);
	}
    
	model.addAttribute("jobs",jobs);
	model.addAttribute("currentPage", page);
	model.addAttribute("pageSize", size);
	model.addAttribute("totalPages", jobs.getTotalPages());
    return "jobs";
}

/*	--------------||	user goto apply page	||--------------- */
@GetMapping("/user/apply")
public String applyPage(@RequestParam("id") long jobId,Model model,Principal principal) {
	if(principal==null) {
		return "login";
	}
    Job job = jdao.searchJobById(jobId);

    // Check logged-in user
    if (principal != null) {
        String email = principal.getName();
        User user = userdao.findByuserEmail(email);
        model.addAttribute("user", user);
    }

    // Check job exists
    if (job == null) {
        model.addAttribute("message", "Job not found!");
        return "applyjob";
    }

    model.addAttribute("job", job);

    return "applyjob";
}

/*	------------||	User can see full details of job	||-------------		*/
@GetMapping("/user/jobs/jobDetail")
public String detailPage(@RequestParam("id")long jobId , Model model,Principal principal) {
	Job job = jdao.searchJobById(jobId);
	// Check logged-in user
    if (principal != null) {
        String email = principal.getName();
        User user = userdao.findByuserEmail(email);
        model.addAttribute("user", user);
    }

	if(job==null) {
		model.addAttribute("message","job not found!");
	}else {
		model.addAttribute("jobDetail",job);
	}
    return "jobdetails";
}

/*	----------------||	User profile view(editable) 	||-----------------	*/
@GetMapping("/userdetail")
public String userDetailPage(Principal principal, Model model) {
	Map<String, Integer> userAppCount =new HashMap<String, Integer>();
	if(principal==null) {
		return "login";
	}
	String email= principal.getName();
	User user = userdao.findByuserEmail(email);
	int applications = 0;
	int interviews= 0;
	if(user!=null) {
		List<UserApplication> lst = userdao.getAlluserApplications(user);
		if(lst != null && !lst.isEmpty()) {
			
	    applications = lst.size();
		
		for(UserApplication app: lst) {
			if(app.getStatus()==ApplicationStatus.APPROVED) {
				interviews++;
			}
		}
		}
		
		model.addAttribute("user",user);
	}
	userAppCount.put("applications", applications);
	userAppCount.put("interviews", interviews);
	model.addAttribute("userAppCount",userAppCount);	
    return "userdetail";
}



}
