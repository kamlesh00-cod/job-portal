
/*=================================================
	 * 			JobPortal Project
	 * 	  
	 *      	Git(kamlesh00-cod)
	 * ================================================*/
package com.jobportal.contollers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.dao.UserDAO;
import com.jobportal.pojo.User;
import com.jobportal.services.EmailVerifier;
import com.jobportal.services.MailService;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userdao;
	@Autowired
	private MailService emailService;
	@Autowired
	private EmailVerifier eVer;
	/*=======================================
			User register submit form
	=========================================*/
// -------------------------_ OTP _--------------------------
	@PostMapping("/sendotp")
	public ResponseEntity<Map<String, String>> sendOTPToUserEmail(@RequestParam String email)throws Exception{
		Map<String , String> response = new HashMap<String, String>();
		if(eVer.otpGenerator(email)) {
			response.put("status","success");
			response.put("message", "OTP sended to your email");
			return ResponseEntity.ok(response);
		}else {
			response.put("status","error");
			response.put("message", "OTP already sent. Please wait. ");
			return ResponseEntity.badRequest().body(response);
		}
	}
// --------------------------_ Verify OTP _----------------------
	@PostMapping("/verifyotp")
	public ResponseEntity<Map<String, String>> verifyUserEmail(@RequestParam String email,@RequestParam("inputOtp")Integer userOTP) {
			Map<String , String> response = new HashMap<String, String>();
			if(eVer.verifyOTP(email,userOTP)) {
				response.put("status","success");
				response.put("message", "OTP Verified");
				return ResponseEntity.ok(response);
			}else {
				response.put("status","error");
				response.put("message", "OTP Invalid");
				return ResponseEntity.badRequest().body(response);
			}
	}
// -------------------------_ Register User _--------------------------
	 @PostMapping("/submitform")
	 public ResponseEntity<Map<String, String>> registerUser(@ModelAttribute User user) throws Exception{
		 Map<String, String> response = new HashMap<String, String>();
		 if(userdao.regUser(user)) {
			 response.put("status", "success");
			 response.put("message", "registered");
			
			 emailService.sendSuccessRegisterMail(user.getEmail(),user.getName());
			
			return ResponseEntity.ok(response);
		 }else {
			 response.put("status", "error");
			 response.put("message","You are already registered. Please use another email.");
			 return ResponseEntity.badRequest().body(response);
		 }
		 
	 }
	 
	 /*=======================================
				User Update form
		=========================================*/
	@PutMapping("/user/userdetail/updateinfo")
	public ResponseEntity<Map<String, String>> updateUserByUser(@ModelAttribute User user){
		Map<String, String> response = new HashMap<String, String>();
		if(userdao.updateUserInfo(user)) {
			response.put("status", "success");
			response.put("message", "Info updated!");
			return ResponseEntity.ok(response);
		}
		response.put("status", "error");
		response.put("message", "Info not updated! Try later. ");
		
		return ResponseEntity.badRequest().body(response);
	}
	
		/*=======================================
				User Profile Picture upload 
		=========================================*/
	@PutMapping("/user/userdetail/updateprofileimg")
	public ResponseEntity<Map<String, String>> uploadProfileImage(@RequestParam("id")long userId,@RequestParam("profilePic") MultipartFile file) {
		Map<String, String> response = new HashMap<String, String>();
		long maxSize = 10 * 1024 * 1024; // 10 MB
		if (file.getSize() > maxSize) {
			 response.put("status", "error");
			 response.put("message", "File size must be less than 10MB");
		    return ResponseEntity.badRequest().body(response);
		}
		if(file==null||file.isEmpty()) {
			response.put("status", "error");
		     response.put("message", "Please upload a file!");
			return ResponseEntity.badRequest().body(response);
		}
		String contentType = file.getContentType();

		if (contentType == null ||!(contentType.equals("image/jpeg")|| contentType.equals("image/png")|| contentType.equals("image/webp"))) {
			response.put("status", "error");
			response.put("message", "Only JPG, PNG, WEBP images are allowed!");
		    return ResponseEntity.badRequest().body(response);
		}
		if(userdao.uploadProfilePic(userId, file)) {
			response.put("status", "success");
			response.put("message", "Picture uploaded successfully!");
			return ResponseEntity.ok(response);
		}else {
			response.put("status", "error");
			response.put("message","Error uploading picture!");
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	/*=========================================
	 * 			 User Reset login
	 * =======================================*/
	// -------------------------_Forgot Password_--------------------------
	 @PostMapping("/forgotpass")
	 public ResponseEntity<Map<String, String>> resetUserLogin(@RequestParam String email ) throws Exception{
		 Map<String, String> response = new HashMap<String, String>();
		 if(eVer.passwordChangeByEmail(email)) {
			 response.put("status", "success");
			 response.put("message", "OTP Sent to Your Email");
			
			return ResponseEntity.ok(response);
		 }else {
			 response.put("status", "error");
			 response.put("message","Entered Email Not Found.");
			 return ResponseEntity.badRequest().body(response);
		 }
		 
	 }
	// -------------------------_Reset Password_--------------------------
		 @PostMapping("/resetpass")
		 public ResponseEntity<Map<String, String>> updateLogin(@RequestParam String email, @RequestParam String password ) throws Exception{
			 Map<String, String> response = new HashMap<String, String>();
			 if(eVer.updatePasswordByEmail(email, password)) {
				 response.put("status", "success");
				 response.put("message", "Password Changed Succesfully!");
				 
				return ResponseEntity.ok(response);
			 }else {
				 response.put("status", "error");
				 response.put("message","invalid!Error on reseting password!");
				 return ResponseEntity.badRequest().body(response);
			 }
			 
		 }
}
