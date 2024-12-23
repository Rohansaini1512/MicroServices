package com.rohan.jobms.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.hc.core5.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// import com.netflix.discovery.converters.Auto;
import com.rohan.jobms.job.Job;
import com.rohan.jobms.job.JobRepository;
import com.rohan.jobms.job.JobService;
import com.rohan.jobms.job.clients.CompanyClient;
import com.rohan.jobms.job.clients.ReviewClient;
import com.rohan.jobms.job.dto.JobDTO;
import com.rohan.jobms.job.external.Company;
import com.rohan.jobms.job.external.Review;
import com.rohan.jobms.job.mapper.JobMapper;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository , 
    CompanyClient companyClient , ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobWithCompanyDTOs = new ArrayList<>();

        
        return jobs.stream().map(this:: convertToDto)
                .collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job){
            // JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            // jobWithCompanyDTO.setJob(job);
            // RestTemplate restTemplate = new RestTemplate();
            // try {
                // Company company = restTemplate.getForObject(
                //     "http://COMPANYMS:8081/companies/" + job.getCompanyId(),
                //     Company.class
                // );
                Company company = companyClient.getCompany(job.getCompanyId());
                // ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                // "http://REVIEWSMS:8083/reviews?companyId=" + job.getCompanyId(),
                // HttpMethod.GET,
                // HttpEntity.EMPTY,
                // new ParameterizedTypeReference<List<Review>>() {}
                // );
                List<Review>reviews = reviewClient.getReviews(job.getCompanyId());
;

                // List<Review> review = reviewResponse.getBody();

                JobDTO jobWithCompanyDTO = JobMapper.
                mapToJobWithCompanyDto(job, company , reviews);
                // jobWithCompanyDTO.setCompany(company);
                // jobWithCompanyDTO.setCompany(null); // Handle missing company gracefully
            // }
            return jobWithCompanyDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
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
