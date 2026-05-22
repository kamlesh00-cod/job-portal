
/*=================================================
* 			JobPortal Project
* 	
*     	 	git(kamlesh00-cod)
* ================================================*/
// --------- [This Class Used For Login Spring Boot Basic Authentication ] --------------
package com.jobportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobportal.dao.UserDAO;
import com.jobportal.pojo.User;
@Service
public class CustomeUserDetailService implements UserDetailsService {
	@Autowired
	private UserDAO userdao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userdao.findByuserEmail(username);
		if(user==null) {
			new UsernameNotFoundException("User not found!");
		}
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getEmail()).password(user.getPassword()).roles(user.getRole().name())
				.build();
	}
	
	
}
