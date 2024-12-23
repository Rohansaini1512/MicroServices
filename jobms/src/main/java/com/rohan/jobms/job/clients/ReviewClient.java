package com.rohan.jobms.job.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rohan.jobms.job.external.Review;

@FeignClient(name = "REVIEWSMS")
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Review>getReviews(@RequestParam("companyId") Long companyId);
}
