package com.kishan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishan.exception.JobApplicationNotFoundException;
import com.kishan.model.JobApplication;
import com.kishan.repository.IJobApplicationRepository;

@Service
public class JobApplicationServiceImpl implements IJobApplicationService {
	
	@Autowired
	private IJobApplicationRepository jrepo;

	@Override
	public JobApplication addJobApplication(JobApplication jobApplication) {
		return jrepo.save(jobApplication);
	}

	@Override
	public List<JobApplication> getAllJobApplications() {
		return jrepo.findAll();
	}

	@Override
	public JobApplication getJobApplicationById(Integer jid) {
		Optional<JobApplication> optional = jrepo.findById(jid);

	    if (optional.isPresent()) {
	        return optional.get();
	    }

	    throw new JobApplicationNotFoundException(
	            "Job application not found with ID: " + jid);
	}

	@Override
	public JobApplication updateJobApplication(JobApplication jobApplication) {

		 Optional<JobApplication> optional =
		            jrepo.findById(jobApplication.getJid());

		    if (optional.isPresent()) {

		        JobApplication existing = optional.get();

		        existing.setCompanyName(jobApplication.getCompanyName());
		        existing.setJobRole(jobApplication.getJobRole());
		        existing.setLocation(jobApplication.getLocation());
		        existing.setSalary(jobApplication.getSalary());
		        existing.setStatus(jobApplication.getStatus());
		        existing.setAppliedDate(jobApplication.getAppliedDate());
		        existing.setFollowUpDate(jobApplication.getFollowUpDate());

		        return jrepo.save(existing);
		    }

		    throw new JobApplicationNotFoundException(
		            "Job application not found with ID: "
		            + jobApplication.getJid());
	}

	@Override
	public void deleteJobApplication(Integer jid) {
		 if (jrepo.existsById(jid)) {
		        jrepo.deleteById(jid);
		    } else {
		        throw new JobApplicationNotFoundException(
		                "Job application not found with ID: " + jid);
		    }

	}

	@Override
	public List<JobApplication> searchByCompany(String companyName) {
		 return jrepo.findByCompanyNameContainingIgnoreCase(companyName);
	}

	@Override
	public List<JobApplication> getByStatus(String status) {
		 return jrepo.findByStatusIgnoreCase(status);
	}

}
