package com.sc2006.hawker.service;

import com.sc2006.hawker.model.FoodStall;
import com.sc2006.hawker.model.Hawker;
import com.sc2006.hawker.model.Review;
import com.sc2006.hawker.model.User;
import com.sc2006.hawker.repository.ReviewRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final MongoTemplate mongoTemplate;

    public ReviewService(MongoTemplate mongoTemplate, ReviewRepository reviewRepository) {
        this.mongoTemplate = mongoTemplate;
        this.reviewRepository = reviewRepository;
    }

    /**
     * Get all reviews
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Get review by serial number
     *
     * @param serialNo serial number
     * @return review
     */
    public Optional<Review> getReviewBySerialNo(String serialNo) {
        return reviewRepository.findBySerialNo(serialNo);
    }

    /**
     * Create a review
     *
     * @param reviewBody     review body
     * @param rating         rating
     * @param hawkerSerialno serial number of hawker
     * @param userName       name of user
     * @return review
     */
    public Review createReviewWithHawker(String reviewBody, Integer rating, String hawkerSerialno, String userName) {
        Review review = reviewRepository.insert(new Review(reviewBody, rating, hawkerSerialno, "", userName));
        mongoTemplate.update(Hawker.class)
                .matching(Criteria.where("serialno").is(hawkerSerialno))
                .apply(new Update().push("reviews").value(review))
                .first();
        mongoTemplate.update(User.class)
                .matching(Criteria.where("username").is(userName))
                .apply(new Update().push("reviews").value(review))
                .first();
        return review;
    }

    /**
     * Create a review
     *
     * @param reviewBody        review body
     * @param rating            rating
     * @param foodStallSerialno food stall serial number
     * @param userName          name of user
     * @return review
     */
    public Review createReviewWithFoodStall(String reviewBody, Integer rating, String foodStallSerialno, String userName) {
        Review review = reviewRepository.insert(new Review(reviewBody, rating, "", foodStallSerialno, userName));
        mongoTemplate.update(FoodStall.class)
                .matching(Query.query(Criteria.where("serialno").is(foodStallSerialno)))
                .apply(new Update().push("reviews", review))
                .first();
        mongoTemplate.update(User.class)
                .matching(Query.query(Criteria.where("username").is(userName)))
                .apply(new Update().push("reviews", review))
                .first();
        return review;
    }

    public List<Review> getReviewsByHawkerSerialNo(String hawkerSerialNo) {
        return mongoTemplate.find(Query.query(Criteria.where("hawkerSerialNo").is(hawkerSerialNo)), Review.class);
    }
}
