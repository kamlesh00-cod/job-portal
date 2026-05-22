/*=================================================
* 			JobPortal Project
* 	
*     	 	Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.jobportal.pojo.Job;

public interface JobRepository extends CrudRepository<Job, Long>,JpaRepository<Job, Long> {
	/*
	 * @Query("select j form Job j where j.title like concat('%',:keyword,'%')")
	 * List<Job> searchJob(@Param("keyword") String keyword);
	 */
	List<Job> findByTitle(String title);
}
