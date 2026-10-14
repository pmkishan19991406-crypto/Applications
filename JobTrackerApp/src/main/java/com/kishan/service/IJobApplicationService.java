package com.kishan.service;

import java.util.List;

import com.kishan.model.JobApplication;

public interface IJobApplicationService {

	public JobApplication addJobApplication(JobApplication jobApplication);

    public List<JobApplication> getAllJobApplications();

    public JobApplication getJobApplicationById(Integer jid);

    public JobApplication updateJobApplication(JobApplication jobApplication);

    public void deleteJobApplication(Integer jid);
    
    List<JobApplication> searchByCompany(String companyName);

    List<JobApplication> getByStatus(String status);
}
