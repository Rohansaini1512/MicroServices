package com.rohan.jobms.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// import com.netflix.discovery.converters.Auto;
import com.rohan.jobms.job.Job;
import com.rohan.jobms.job.JobRepository;
import com.rohan.jobms.job.JobService;
import com.rohan.jobms.job.dto.JobWithCompanyDTO;
import com.rohan.jobms.job.external.Company;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();

        
        return jobs.stream().map(this:: convertToDto)
                .collect(Collectors.toList());
    }

    private JobWithCompanyDTO convertToDto(Job job){
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            // RestTemplate restTemplate = new RestTemplate();
            try {
                Company company = restTemplate.getForObject(
                    "http://COMPANYMS:8081/companies/" + job.getCompanyId(),
                    Company.class
                );
                jobWithCompanyDTO.setCompany(company);
            } catch (Exception e) {
                jobWithCompanyDTO.setCompany(null); // Handle missing company gracefully
            }
            return jobWithCompanyDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            job.setCompanyId(updatedJob.getCompanyId());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
