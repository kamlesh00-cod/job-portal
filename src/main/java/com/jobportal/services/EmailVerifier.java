
/*=================================================
* 			JobPortal Project
* 	  
*      		git(kamlesh00-cod)
* ================================================*/
package com.jobportal.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dao.UserDAO;
import com.jobportal.pojo.User;

@Service
public class EmailVerifier {
		
		@Autowired
		private MailService emailService;
		@Autowired
		private UserDAO udao;
		
		//Store every request
		private Map<String, Integer> otpStore =new HashMap<String, Integer>();
		private Map<String, Long> timeStore = new HashMap<String, Long>();
		
		//----------------------------- Send OTP to Email --------------------------
		public boolean otpGenerator(String email) throws Exception {
			
			Random rand = new Random();
				int otp = 100000 + rand.nextInt(900000);
				long currTime = System.currentTimeMillis();
				emailService.sendOTPMail(email, otp);
				otpStore.put(email, otp);
				timeStore.put(email, currTime);
				//System.out.println(otpStore.toString()+" _ _ "+timeStore.toString());
				return true;
		}
		
		//------------------------------- Verify Sent OTP ---------------------------
		public boolean verifyOTP(String email,int userOTP) {
			long timeDiff=2*60*1000;
			Long currTime=timeStore.get(email);
			Integer savedOTP =otpStore.get(email);
			if(currTime==null||savedOTP==null) {
				return false;
			}
			long nowTime=System.currentTimeMillis();
			 if ((nowTime - currTime) > timeDiff) {

		            otpStore.remove(email);
		            timeStore.remove(email);

		            return false;
		        }
			   if (savedOTP == userOTP) {

		            otpStore.remove(email);
		            timeStore.remove(email);

		            return true;
		        }

			return false;
		}
//		
		/*==========================================
		 * 			Forgot Password
		 * =========================================*/
		// ---------------_ Send OTP to Email _-----------------
		public boolean passwordChangeByEmail(String email) {
			User user = udao.findByuserEmail(email);
			if(user==null) {
				return false;
			}
			try {
				if( otpGenerator(user.getEmail())) {
					
					return true;
				}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		// ---------------_ Verify with secureKey And Update PASSWORD _-----------------
		public boolean updatePasswordByEmail(String email,String password) {
		
			
			User user = udao.findByuserEmail(email);
			if(user==null) {
				return false;
			}else {
			
				udao.updatePassword(user.getId(), password);
				
				return true;
			}
			
			
		}
}
