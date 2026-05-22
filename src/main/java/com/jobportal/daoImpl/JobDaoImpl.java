/*=================================================
* 			JobPortal Project
* 	  
*      		Git(kamlesh00-cod)
* ================================================*/
package com.jobportal.daoImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.jobportal.dao.JobDAO;
import com.jobportal.pojo.Job;
import com.jobportal.repository.JobRepository;
@Component
public class JobDaoImpl implements JobDAO {

	@Autowired
	private JobRepository jobRepo;
	/*=================================================
	 * 			 	Save Jobs  
	 * ================================================*/
	@Override
	public boolean saveJob(Job j) {
		try {
			Optional<Job> op = jobRepo.findById(j.getId());
			if(op.isPresent()) {
				return false;
			}else {
				jobRepo.save(j);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*=================================================
	 * 				Update Jobs 
	 * ================================================*/
	@Override
	public boolean updateJob(Job j) {
		try {
			Optional<Job> op = jobRepo.findById(j.getId());
			if(op.isPresent()) {
				
				 Job existJob = op.get(); 
				 if(j.getTitle()!=null) {
					 existJob.setTitle( j.getTitle()); }
				 if(j.getCompany()!=null) {
					 existJob.setCompany(j.getCompany()); }
				  if(j.getJobType()!=null) {
					  existJob.setJobType(j.getJobType()); }
				 if(j.getLocation()!=null) {
					 existJob.setLocation(j.getLocation()); }
				 if(j.getDescription()!=null) {
					 existJob.setDescription(j.getDescription()); }				 
				jobRepo.save(existJob);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}

	/*=================================================
	 * 				Delete Jobs 
	 * ================================================*/
	@Override
	public boolean deleteJob(long jobId) {
		
		try {
			jobRepo.deleteById(jobId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*=================================================
	 * 			 	Get All Jobs  
	 * ================================================*/
	@Override
	public List<Job> getAllJobs() {
		try {
			return (List<Job>)jobRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	/*=================================================
	 * 			 Use Pagination For Get jobs 
	 * ================================================*/
	@Override
	public Page<Job> getAllJobs(int page,int size) {
		try {
			Pageable pageable = PageRequest.of(page, size);
			Page<Job> job = (Page<Job>)jobRepo.findAll(pageable);
			if(job==null)
				return Page.empty() ;
			return job;
		} catch (Exception e) {
			e.printStackTrace();
			return Page.empty();
		} 
	}

	/*=================================================
	 * 					Search Job 
	 * ================================================*/
	//---------------_ by Id _----------------------
	@Override
	public Job searchJobById(long id) {
		try {
			Optional<Job> op = jobRepo.findById(id);
			if(op.isPresent()) {
				Job job = op.get();
				System.out.println(job);
				return job;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//---------------_ by Name _----------------------
	@Override
	public List<Job> searchJobByName(String jobName) {
		try {
			List<Job> search = jobRepo.findByTitle(jobName);
			if(search!=null) {
				
				return search;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
}
