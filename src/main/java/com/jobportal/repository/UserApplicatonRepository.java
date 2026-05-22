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

import com.jobportal.pojo.Job;
import com.jobportal.pojo.User;
import com.jobportal.pojo.UserApplication;

public interface UserApplicatonRepository extends CrudRepository<UserApplication, Long> {

	@Query("select ua from UserApplication ua where ua.user= :user ")
	List<UserApplication> findByUserInfo(@Param("user") User user);
	@Query("select ua from UserApplication ua where ua.user= :user and ua.job=:job")
	Optional<UserApplication> findByUserAndJob(User user,Job job);
	@Query("select ua from UserApplication ua where lower(ua.user.name) like lower(concat('%', :keyword, '%'))")
	List<UserApplication> searchUserApplication(@Param("keyword") String keyword);
}
