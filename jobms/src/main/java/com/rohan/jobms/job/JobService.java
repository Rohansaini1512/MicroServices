package com.rohan.jobms.job;

import java.util.List;

import com.rohan.jobms.job.dto.JobWithCompanyDTO;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    JobWithCompanyDTO getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id, Job updatedJob);
}
