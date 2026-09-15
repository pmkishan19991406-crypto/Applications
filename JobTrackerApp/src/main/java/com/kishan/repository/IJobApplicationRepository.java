package com.kishan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishan.model.JobApplication;

public interface IJobApplicationRepository
        extends JpaRepository<JobApplication, Integer> {

    List<JobApplication> findByCompanyNameContainingIgnoreCase( String companyName);

    List<JobApplication> findByStatusIgnoreCase(String status);
}