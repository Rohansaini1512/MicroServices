package com.rohan.reviewsms.review;

import java.util.List;

// import org.springframework.http.HttpStatus;

public interface ReviewService {
    List<Review>getAllReviews(Long companyId);
    boolean addReview(Long companyId , Review review);
    Review getReview(Long reviewId);
    boolean updateReview( Long reviewId , Review review);
    boolean deleteReview( Long reviewId);
}
