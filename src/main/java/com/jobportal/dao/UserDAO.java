/*=================================================
* 			JobPortal Project
* 	  
*      		Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jobportal.pojo.User;
import com.jobportal.pojo.UserApplication;

public interface UserDAO {
	
	
		//register user
		boolean regUser(User user);
		boolean regAdmin(User user);
		//login user
		User userLogin(User user);
	//Manage users
		//view Registered users
		List<User> getAllUsers();
		//Delete User
		boolean deleteUser(long id);
		//block/Active users
		boolean userStatus(long id);
		//search user
		User searchUserById(long id);
		User findByuserEmail(String email);
		List<User> searchByName(String name);
		//update user info
		boolean updateUserInfo(User user);
		boolean uploadProfilePic(long id, MultipartFile file);
		boolean updatePassword (long id ,String password);
		//search user Applications
		List<UserApplication> getAlluserApplications(User user);
}
