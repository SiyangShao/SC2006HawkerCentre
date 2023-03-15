package com.sc2006.hawker.service;

import com.sc2006.hawker.model.FoodStall;
import com.sc2006.hawker.model.Hawker;
import com.sc2006.hawker.model.Review;
import com.sc2006.hawker.model.User;
import com.sc2006.hawker.repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

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
     * Find review by id
     *
     * @param id id of review
     * @return review
     */
    public Optional<Review> findById(String id) {
        return reviewRepository.findById(new ObjectId(id));
    }

    /**
     * Find all reviews
     *
     * @return all reviews
     */
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    /**
     * Create a review
     *
     * @param reviewBody body of review
     * @param rating     rating of review
     * @param hawkerId   id of hawker
     * @param userId     id of user
     * @return review with hawker
     */
    public Review createReviewWithHawker(String reviewBody, Integer rating, ObjectId hawkerId, ObjectId userId) {
        User user = mongoTemplate.findById(userId, User.class);
        Hawker hawker = mongoTemplate.findById(hawkerId, Hawker.class);
        Review review = reviewRepository.insert(new Review(reviewBody, rating, hawker, user));
        mongoTemplate.update(Hawker.class)
                .matching(Query.query(Criteria.where("id").is(hawkerId)))
                .apply(Update.update("reviews", review))
                .first();
        mongoTemplate.update(User.class)
                .matching(Query.query(Criteria.where("id").is(userId)))
                .apply(Update.update("reviews", review))
                .first();
        return review;
    }

    /**
     * Create a review
     *
     * @param reviewBody  body of review
     * @param rating      rating of review
     * @param foodStallId id of food stall
     * @param userId      id of user
     * @return review with food stall
     */
    public Review createReviewWithFoodStall(String reviewBody, Integer rating, ObjectId foodStallId, ObjectId userId) {
        User user = mongoTemplate.findById(userId, User.class);
        FoodStall foodStall = mongoTemplate.findById(foodStallId, FoodStall.class);
        Review review = reviewRepository.insert(new Review(reviewBody, rating, foodStall, user));
        mongoTemplate.update(FoodStall.class)
                .matching(Query.query(Criteria.where("id").is(foodStallId)))
                .apply(Update.update("reviews", review))
                .first();
        mongoTemplate.update(User.class)
                .matching(Query.query(Criteria.where("id").is(userId)))
                .apply(Update.update("reviews", review))
                .first();
        return review;
    }
}
