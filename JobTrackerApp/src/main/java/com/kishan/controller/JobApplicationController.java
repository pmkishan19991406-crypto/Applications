package com.kishan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kishan.model.JobApplication;
import com.kishan.service.IJobApplicationService;

import jakarta.validation.Valid;

@RestController
public class JobApplicationController {
	
	@Autowired
	private IJobApplicationService jservice;

	@PostMapping("/api/applications")
	public JobApplication addJobApplication(@Valid @RequestBody JobApplication jobApplication) {

	    return jservice.addJobApplication(jobApplication);
	}
	
	@GetMapping("/api/applications")
	public List<JobApplication> getAllJobApplications() {

	    return jservice.getAllJobApplications();
	}
	
	@GetMapping("/api/applications/{jid}")
	public JobApplication getJobApplicationById(@PathVariable Integer jid) {

	    return jservice.getJobApplicationById(jid);
	}
	
	@PutMapping("/api/applications")
	public JobApplication updateJobApplication(
	        @RequestBody JobApplication jobApplication) {

	    return jservice.updateJobApplication(jobApplication);
	}
	
	@DeleteMapping("/api/applications/{jid}")
	public void deleteJobApplication(@PathVariable Integer jid) {

	    jservice.deleteJobApplication(jid);
	}
	
	@GetMapping("/api/applications/search")
	public List<JobApplication> searchByCompany(@RequestParam String companyName) {

	    return jservice.searchByCompany(companyName);
	}
	
	@GetMapping("/api/applications/status/{status}")
	public List<JobApplication> getByStatus(
	        @PathVariable String status) {

	    return jservice.getByStatus(status);
	}
	
}
