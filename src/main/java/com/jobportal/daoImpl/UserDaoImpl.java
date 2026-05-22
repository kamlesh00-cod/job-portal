
/*=================================================
	 * 			JobPortal Project
	 * 	 
	 *     		 Git(kamlesh00-cod)
	 * ================================================*/
package com.jobportal.daoImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.dao.UserDAO;
import com.jobportal.enums.UserRoles;
import com.jobportal.enums.UserStatus;
import com.jobportal.pojo.User;
import com.jobportal.pojo.UserApplication;
import com.jobportal.repository.UserApplicatonRepository;
import com.jobportal.repository.UserRepository;
@Component
public class UserDaoImpl implements UserDAO{
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserApplicatonRepository appRepo;
	
	  @Autowired 
	  private PasswordEncoder passwordEncoder;
	 
	/*==========================================
	 * 	 User registration(save user in DB)
	 *=========================================== */
	  //-----------------------------_ User save in db _-----------------------
	@Override
	public boolean regUser(User user) {
		
		 try { Optional<User> op = userRepo.findByEmail(user.getEmail());
		  		if(op.isPresent()) { return false; 
		  		}else {
		  			user.setPassword(passwordEncoder.encode(user.getPassword()));
		  			user.setEmail(user.getEmail().toLowerCase());
		  			user.setRole(UserRoles.USER);
		  			user.setStatus(UserStatus.ACTIVE);
		  			userRepo.save(user); 
		  			return true; 
		  		} 
		  } catch (Exception e) { throw e; }
		 
		
	}
	//---------------------------_ Admin Save in db _-----------------------------
	@Override
		public boolean regAdmin(User user) {
			try { Optional<User> op = userRepo.findByEmail(user.getEmail());
	  		if(op.isPresent()) { return false; 
	  		}else {
	  			
	  			userRepo.save(user); 
	  			return true; 
	  		} 
			} catch (Exception e) { throw e; }
	 
		}
	/*==========================================
	 * 		 user Login(Check credential)
	 *=========================================== */
	@Override
	public User userLogin(User user) {
		
		return null;
	}
	
	

	/*==========================================
	 * 			List of users
	 *=========================================== */
	@Override
	public List<User> getAllUsers() {
		    try {
		        return (List<User>) userRepo.findAll();
		    } catch (Exception e) {
		        e.printStackTrace();
		        return Collections.emptyList();
		    }
		
	}
	
	/*==========================================
	 * 			delete user
	 *=========================================== */
	@Override
	public boolean deleteUser(long id) {
		try {
			Optional<User> op = userRepo.findById(id);
			if(op.isPresent()) {
				userRepo.delete(op.get());
				return true;
			}else
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*==========================================
	 * 			block/active user
	 *=========================================== */
	@Override
	public boolean userStatus(long id) {
		try {
			Optional<User> op = userRepo.findById(id);
			if(op.isEmpty())
				return false;
			User u = op.get();
			u.setStatus(u.getStatus()==UserStatus.ACTIVE ? UserStatus.BLOCKED : UserStatus.ACTIVE);
			userRepo.save(u);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	
	/*==========================================
	 * 			find user by id or email
	 *=========================================== */
	// --------------_ by id _-------------------
	@Override
	public User searchUserById(long id) {
		try {
			Optional<User> op = userRepo.findById(id);
			if(op.isPresent()) {
				
				return op.get();
			}else
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//---------------_ by Email _----------------------
	@Override
	public User findByuserEmail(String email) {
		try {
			Optional<User> op = userRepo.findByEmail(email);
			if(op.isPresent()) {
				
				return op.get();
			}else
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//---------------_ by Name _----------------------
	@Override
	public List<User> searchByName(String name) {
		try {
			List<User> search = userRepo.searchUser(name);
			if(search!=null) {
				
				return search;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	/*==========================================
	 * 			Update info by user
	 *=========================================== */
	@Override
	public boolean updateUserInfo(User user ) {
		try {
			Optional<User> op = userRepo.findById(user.getId());
			if(op.isPresent()) {
				User existingUser = op.get();
				if(user.getName()!=null)
					existingUser.setName(user.getName());
				if(user.getEmail()!=null) 
					existingUser.setEmail(user.getEmail());
				if(user.getBio()!=null) 
					existingUser.setBio(user.getBio());
				userRepo.save(existingUser);
				return true;
			}else
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//------------------_ Update Password _------------------------
	@Override
	public boolean updatePassword(long id, String password) {
		try {
			Optional<User> op= userRepo.findById(id);
			if(op.isPresent()) {
				User u = op.get();
				u.setPassword(passwordEncoder.encode(password));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*==========================================
	 * 			Upload profile picture
	 *=========================================== */
	@Override
	public boolean uploadProfilePic(long id, MultipartFile file) {
		
		try {
			 Optional<User> op= userRepo.findById(id);
			 
			 if(op.isEmpty()||file==null||file.isEmpty()){
				 return false;
			 }
			 String fileName= UUID.randomUUID()+"_" +file.getOriginalFilename();
			 Path path = Paths.get("D:\\coding folder\\j2ee\\jobportal\\src\\main\\webapp\\profilePic");
			 Path filePath= path.resolve(fileName);
			 
			 User existUser= op.get();
			 existUser.setProfilePicture(fileName);
			 userRepo.save(existUser);
			 Files.copy(file.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
			 return true;
			 
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*==========================================
	 * 			All user applications list
	 *=========================================== */
	@Override
	public List<UserApplication> getAlluserApplications(User user) {
		try {
			List<UserApplication> lst = appRepo.findByUserInfo(user);
			if( lst==null||lst.isEmpty()) {
				return Collections.emptyList();
			}
			return lst;
		}catch (Exception e) {
			throw e;
		}

	}
	
	
}
