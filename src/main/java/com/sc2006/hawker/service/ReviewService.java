package com.sc2006.hawker.service;

import com.sc2006.hawker.model.Hawker;
import com.sc2006.hawker.model.Review;
import com.sc2006.hawker.repository.ReviewRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    private final MongoTemplate mongoTemplate;

    public ReviewService(MongoTemplate mongoTemplate, ReviewRepository reviewRepository) {
        this.mongoTemplate = mongoTemplate;
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(String reviewBody, String serialno){
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Hawker.class)
                .matching(Criteria.where("serialno").is(serialno))
                .apply(new Update().push("reviews").value(review))
                .first();

        return review;
    }

    /**
     * Update review
     * @param reviewBody - review body
     * @param serialno - serial number of hawker
     * @return - updated review
     */
    public Review updateReview(String reviewBody, String serialno) {
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Hawker.class)
                .matching(Criteria.where("serialno").is(serialno))
                .apply(new Update().push("reviews").value(review))
                .first();

        return review;
    }


    /**
     * Delete review
     * @param reviewBody - review body
     * @param serialno - serial number of hawker
     * @return - updated review
     */
    public Review deleteReview(String reviewBody, String serialno) {
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Hawker.class)
                .matching(Criteria.where("serialno").is(serialno))
                .apply(new Update().pull("reviews", Query.query(Criteria.where("id").is(review.getId()))))
                .first();

        return review;
    }

}
