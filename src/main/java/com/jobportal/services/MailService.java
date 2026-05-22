/*=================================================
* 			JobPortal Project
* 	  
*      		git(kamlesh00-cod)
* ================================================*/
//---------- [This class Have All Email Messages Contains (HTML)]-------------
package com.jobportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	//------ [This method only proto-type ] ------------
	public void sendMessage(String to,String subject,String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
	}
	// -------------------------_Send otp to email_--------------------------
	public void sendOTPMail(String to , int otp) throws Exception{
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setTo(to);
		helper.setSubject("Verify Email");
		helper.setText("<table align=\"center\" width=\"500\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#ffffff; padding:30px; border-radius:10px;\">"
				+ "<tr> <td align=\"center\"> <h2 style=\"color:#333333;\">OTP Verification</h2> </td> </tr>"
				+ "<tr> <td> <p style=\"font-size:16px; color:#555555;\"> Hello User, </p>"
				+ "<p style=\"font-size:16px; color:#555555;\"> Your One-Time Password (OTP) for verification is: </p>"
				+ "<div style=\" text-align:center; margin:30px 0; font-size:32px; letter-spacing:5px; font-weight:bold; color:#2d89ef; \"> "+otp+" </div>"
				+ "<p style=\"font-size:14px; color:#777777;\"> This OTP is valid for 2 minutes. </p>"
				+ "<p style=\"font-size:14px; color:#777777;\"> Do not share this OTP with anyone. </p> <br>"
				+ "<p style=\"font-size:14px; color:#999999;\"> Regards,<br> JobPortal </p> </td> </tr> </table>",true);
		mailSender.send(message);
	}
	// -------------------------_Success Register Message_--------------------------
	public void sendSuccessRegisterMail(String to,String name) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		helper.setTo(to);
		helper.setSubject("Registration");
		helper.setText(" <h2>Registration  Successfull</h2> "
				+ "<h4>Welcome 🎉</h4>"
				+"<p>Hello "+name+",\r\n"
				+ "</p>" 
				+" <p>Your account has been successfully created.\r\n"
						+ "Thank you for registering with us.\r\n"
						+ "</p>"
				+ "<p>You can now login and access your dashboard.</p>"
				+ "<p>Login Now</p>"
				+ "<p>If you did not create this account,\r\n"
				+ "please ignore this email.</p>"
				+ "<p>© <strong>2026 JobPortal.</Strong>\r\n"
				+ "All rights reserved.\r\n"
				+ "</p>",true);
		
		mailSender.send(message);
		
	}
	// -------------------------_ Job Applied Message_--------------------------
	public void sendAppliedJobMail(String to,String jobTitle,String company) throws Exception{
			MimeMessage message = mailSender.createMimeMessage();
		
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
			helper.setTo(to);
			helper.setSubject("Job Application Submission");
			helper.setText("<table align=\"center\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\"\r\n"
					+ "         style=\"background:#ffffff; margin-top:30px; border-radius:8px; overflow:hidden;\">\r\n"
					+ "\r\n"
					+ "    <tr>\r\n"
					+ "      <td align=\"center\" style=\"background:#2563eb; padding:20px; color:#ffffff;\">\r\n"
					+ "        <h1 style=\"margin:0;\">Application Submitted</h1>\r\n"
					+ "      </td>\r\n"
					+ "    </tr>\r\n"
					+ "\r\n"
					+ "    <tr>\r\n"
					+ "      <td style=\"padding:30px; color:#333333;\">\r\n"
					+ "        <h2>Hello,Applicant,</h2>\r\n"
					+ "\r\n"
					+ "        <p>\r\n"
					+ "          Thank you for applying for the position of\r\n"
					+ "          <strong>"+jobTitle+"</strong> at\r\n"
					+ "          <strong>"+company+"</strong>.\r\n"
					+ "        </p>\r\n"
					+ "\r\n"
					+ "        <p>\r\n"
					+ "          Your application has been successfully submitted.\r\n"
					+ "          Our recruitment team will review your profile and contact you if you are shortlisted.\r\n"
					+ "        </p>"
					+ " <p style=\"margin-top:25px;\">\r\n"
					+ "          We appreciate your interest in joining our team.\r\n"
					+ "        </p>\r\n"
					+ "\r\n"
					+ "        <p>\r\n"
					+ "          Best regards,<br>\r\n"
					+ "          <strong>"+company+" Recruitment Team</strong>\r\n"
					+ "        </p>\r\n"
					+ "      </td>\r\n"
					+ "    </tr>\r\n"
					+ "\r\n"
					+ "    <tr>\r\n"
					+ "      <td align=\"center\"\r\n"
					+ "          style=\"background:#f3f4f6; padding:15px; font-size:12px; color:#6b7280;\">\r\n"
					+ "        © 2026 jobPortal. All rights reserved.\r\n"
					+ "      </td>\r\n"
					+ "    </tr>\r\n"
					+ "\r\n"
					+ "  </table>",true);
		
			mailSender.send(message);
	}
	// -------------------------_ Job Approval  Message_--------------------------
	public void sendJobApplicationApproveMail(String to,String jobTitle,String company) throws Exception{
		MimeMessage message = mailSender.createMimeMessage();
	
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
	
		helper.setTo(to);
		helper.setSubject("Job Application Update");
		helper.setText(" <table align=\"center\" width=\"600\" style=\"background:#ffffff; border-radius:8px;\">\r\n"
				+ "    <tr>\r\n"
				+ "      <td style=\"background:#16a34a; color:#ffffff; padding:20px; text-align:center;\">\r\n"
				+ "        <h1>Application Approved</h1>\r\n"
				+ "      </td>\r\n"
				+ "    </tr>\r\n"
				+ "\r\n"
				+ "    <tr>\r\n"
				+ "      <td style=\"padding:30px; color:#333333;\">\r\n"
				+ "        <h2>Hello,Applicant,</h2>\r\n"
				+ "\r\n"
				+ "        <p>\r\n"
				+ "          Congratulations! Your application for the position of\r\n"
				+ "          <strong>"+jobTitle+"</strong> at\r\n"
				+ "          <strong>"+company+"</strong> has been approved.\r\n"
				+ "        </p>\r\n"
				+ "\r\n"
				+ "        <p>\r\n"
				+ "          Our team will contact you soon with the next steps regarding the interview or onboarding process.\r\n"
				+ "        </p>\r\n"
				+ "\r\n"
				+ "        <p>\r\n"
				+ "          Thank you for your interest in joining our company.\r\n"
				+ "        </p>\r\n"
				+ "\r\n"
				+ "        <p>\r\n"
				+ "          Best regards,<br>\r\n"
				+ "          <strong>"+company+" HR Team</strong>\r\n"
				+ "        </p>\r\n"
				+ "      </td>\r\n"
				+ "    </tr>\r\n"
				+ "\r\n"
				+ "    <tr>\r\n"
				+ "      <td style=\"background:#f3f4f6; text-align:center; padding:15px; font-size:12px; color:#666;\">\r\n"
				+ "        © 2026 JobPortal. All rights reserved.\r\n"
				+ "      </td>\r\n"
				+ "    </tr>\r\n"
				+ "  </table>",true);
	
		mailSender.send(message);
	}
	// -------------------------_ Job Rejected Message_--------------------------
	public void sendJobApplicationRejectMail(String to,String jobTitle,String company) throws Exception{
		MimeMessage message = mailSender.createMimeMessage();
	
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
	
		helper.setTo(to);
		helper.setSubject("Job Application Update");
		helper.setText("  <table align=\"center\" width=\"600\" style=\"background:#ffffff; border-radius:8px;\">\r\n"
				+ "    <tr>\r\n"
				+ "      <td style=\"background:#dc2626; color:#ffffff; padding:20px; text-align:center;\">\r\n"
				+ "        <h1>Application Update</h1>\r\n"
				+ "      </td>\r\n"
				+ "    </tr>\r\n"
				+ "\r\n"
				+ "    <tr>\r\n"
				+ "      <td style=\"padding:30px; color:#333333;\">\r\n"
				+ "        <h2>Hello {{user_name}},</h2>\r\n"
				+ "\r\n"
				+ "        <p>\r\n"
				+ "          Thank you for applying for the position of\r\n"
				+ "          <strong>"+jobTitle+"</strong> at\r\n"
				+ "          <strong>"+company+"</strong>.\r\n"
				+ "        </p>\r\n"
				+ "\r\n"
				+ "        <p>\r\n"
				+ "          After careful review, we regret to inform you that your application was not selected for this position.\r\n"
				+ "        </p>\r\n"
				+ "\r\n"
				+ "        <p>\r\n"
				+ "          We appreciate your interest in our company and encourage you to apply for future opportunities that match your skills and experience.\r\n"
				+ "        </p>\r\n"
				+ "\r\n"
				+ "        <p>\r\n"
				+ "          We wish you success in your career journey.\r\n"
				+ "        </p>\r\n"
				+ "\r\n"
				+ "        <p>\r\n"
				+ "          Best regards,<br>\r\n"
				+ "          <strong>"+company+" HR Team</strong>\r\n"
				+ "        </p>\r\n"
				+ "      </td>\r\n"
				+ "    </tr>\r\n"
				+ "\r\n"
				+ "    <tr>\r\n"
				+ "      <td style=\"background:#f3f4f6; text-align:center; padding:15px; font-size:12px; color:#666;\">\r\n"
				+ "        © 2026 JobPortal. All rights reserved.\r\n"
				+ "      </td>\r\n"
				+ "    </tr>\r\n"
				+ "  </table>",true);
	
		mailSender.send(message);
	}
	// -------------------------_ Account Activated Message_--------------------------
	public void sendUserActivebMail(String to,String name) throws Exception{
		MimeMessage message = mailSender.createMimeMessage();
	
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
	
		helper.setTo(to);
		helper.setSubject("Account State");
		helper.setText("<table align=\"center\" width=\"600\" style=\"background:#ffffff; border-radius:8px;\">\r\n"
				+ "  <tr>\r\n"
				+ "    <td style=\"background:#16a34a; color:#ffffff; padding:20px; text-align:center;\">\r\n"
				+ "      <h1>Account Activated</h1>\r\n"
				+ "    </td>\r\n"
				+ "  </tr>\r\n"
				+ "\r\n"
				+ "  <tr>\r\n"
				+ "    <td style=\"padding:30px; color:#333333;\">\r\n"
				+ "      <h2>Hello "+name+",</h2>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        Your account has been successfully activated.\r\n"
				+ "      </p>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        You can now log in and access all features of our platform.\r\n"
				+ "      </p>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        Thank you for joining us.\r\n"
				+ "      </p>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        Best regards,<br>\r\n"
				+ "        <strong>JobPortal Team</strong>\r\n"
				+ "      </p>\r\n"
				+ "    </td>\r\n"
				+ "  </tr>\r\n"
				+ "\r\n"
				+ "</table>",true);
	
		mailSender.send(message);
	}
	// -------------------------_ Account Blocked Message_--------------------------
	public void sendUserBlockMail(String to,String name) throws Exception{
		MimeMessage message = mailSender.createMimeMessage();
	
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
	
		helper.setTo(to);
		helper.setSubject("Account State");
		helper.setText("<table align=\"center\" width=\"600\" style=\"background:#ffffff; border-radius:8px;\">\r\n"
				+ "  <tr>\r\n"
				+ "    <td style=\"background:#dc2626; color:#ffffff; padding:20px; text-align:center;\">\r\n"
				+ "      <h1>Account Blocked</h1>\r\n"
				+ "    </td>\r\n"
				+ "  </tr>\r\n"
				+ "\r\n"
				+ "  <tr>\r\n"
				+ "    <td style=\"padding:30px; color:#333333;\">\r\n"
				+ "      <h2>Hello "+name+",</h2>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        Your account has been temporarily blocked due to violation of our policies or suspicious activity.\r\n"
				+ "      </p>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        If you believe this was a mistake, please contact our support team for assistance.\r\n"
				+ "      </p>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        Support Email: support@jobportal.com\r\n"
				+ "      </p>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        Regards,<br>\r\n"
				+ "        <strong>JobPortal Support Team</strong>\r\n"
				+ "      </p>\r\n"
				+ "    </td>\r\n"
				+ "  </tr>\r\n"
				+ "\r\n"
				+ "</table>",true);
	
		mailSender.send(message);
	}
	// -------------------------_ Account Deleted Message_--------------------------
	public void sendUserDeleteMail(String to,String name) throws Exception{
		MimeMessage message = mailSender.createMimeMessage();
	
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
	
		helper.setTo(to);
		helper.setSubject("Account State");
		helper.setText("<table align=\"center\" width=\"600\" style=\"background:#ffffff; border-radius:8px;\">\r\n"
				+ "  <tr>\r\n"
				+ "    <td style=\"background:#6b7280; color:#ffffff; padding:20px; text-align:center;\">\r\n"
				+ "      <h1>Account Deleted</h1>\r\n"
				+ "    </td>\r\n"
				+ "  </tr>\r\n"
				+ "\r\n"
				+ "  <tr>\r\n"
				+ "    <td style=\"padding:30px; color:#333333;\">\r\n"
				+ "      <h2>Hello "+name+",</h2>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        Your account has been permanently deleted from our platform.\r\n"
				+ "      </p>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        If this action was not requested by you or you need assistance, please contact our support team immediately.\r\n"
				+ "      </p>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        Thank you for being with us.\r\n"
				+ "      </p>\r\n"
				+ "\r\n"
				+ "      <p>\r\n"
				+ "        Regards,<br>\r\n"
				+ "        <strong>JobPortal Team</strong>\r\n"
				+ "      </p>\r\n"
				+ "    </td>\r\n"
				+ "  </tr>\r\n"
				+ "\r\n"
				+ "</table>",true);
	
		mailSender.send(message);
	}
}
