/*=================================================
* 			JobPortal Project
* 	  
*      		git(kamlesh00-cod)
* ================================================*/
package com.jobportal.services;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jobportal.dao.UserDAO;
import com.jobportal.enums.UserRoles;
import com.jobportal.enums.UserStatus;
import com.jobportal.pojo.User;

@Component
public class AdminCreation implements CommandLineRunner {
	/*============================================
	 * 				Create admin in console{one time)
	 * ============================================*/
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDAO udao;
	Scanner in = new Scanner(System.in);
	private void createAdmin() {
		String userName;
		String password;
		
		System.out.println("Enter Name: ");
		String name = in.nextLine();
		System.out.println("Enter UserName(Email): ");
		userName = in.nextLine();
		System.out.println("Enter Password: ");
		password = in.nextLine();
		
		User user = new User();
		user.setName(name);
		user.setEmail(userName);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(UserRoles.ADMIN);
		user.setStatus(UserStatus.ACTIVE);
		if(udao.regAdmin(user)){
			System.out.println("\nAdmin Created! \n ---------- Command stop -----------");
		}else {
			System.out.println("Check your UserName! ");
		}
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("\t\t=====================\n  'For Creating Admin. Enter this command('createadmin') ' \n\t\t=======================");
		String commandLine = in.nextLine();
		if("createadmin".equalsIgnoreCase(commandLine.trim())) {
			createAdmin();
		}
		
	}
	
}
