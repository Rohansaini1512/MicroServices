package com.rohan.jobms.job.mapper;

import java.util.List;

import com.rohan.jobms.job.Job;
import com.rohan.jobms.job.dto.JobDTO;
import com.rohan.jobms.job.external.Company;
import com.rohan.jobms.job.external.Review;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(
        Job job,
        Company company, List<Review> reviews
    ){
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReview(reviews);
        return jobWithCompanyDTO;
    }
}
