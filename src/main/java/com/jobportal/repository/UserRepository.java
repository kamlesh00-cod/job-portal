/*=================================================
* 			JobPortal Project
* 	
*     	 	Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jobportal.pojo.User;

public interface UserRepository extends CrudRepository<User, Long> {
	@Query("select u from User u where u.email= :email ")
	Optional<User> findByEmail(@Param("email")String email);
	@Query("select u from User u where lower(u.name) like lower(concat('%', :keyword, '%'))")
	List<User> searchUser(@Param("keyword") String keyword);
}
