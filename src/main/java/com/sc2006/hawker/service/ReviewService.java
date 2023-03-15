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
     * @param reviewBody     review body
     * @param rating         rating
     * @param hawkerSerialno serial number of hawker
     * @param userName       name of user
     * @return review
     */
    public Review createReviewWithHawker(String reviewBody, Integer rating, String hawkerSerialno, String userName) {
        User user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(userName)), User.class);
        Hawker hawker = mongoTemplate.findOne(Query.query(Criteria.where("serialno").is(hawkerSerialno)), Hawker.class);
        Review review = reviewRepository.insert(new Review(reviewBody, rating, hawker, user));
        mongoTemplate.update(Hawker.class)
                .matching(Query.query(Criteria.where("serialno").is(hawkerSerialno)))
                .apply(Update.update("reviews", new Document("$push", new Document("reviews", review))))
                .first();
        mongoTemplate.update(User.class)
                .matching(Query.query(Criteria.where("username").is(userName)))
                .apply(Update.update("reviews", new Document("$push", new Document("reviews", review))))
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
        User user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(userName)), User.class);
        FoodStall foodStall = mongoTemplate.findOne(Query.query(Criteria.where("serialno").is(foodStallSerialno)), FoodStall.class);
        Review review = reviewRepository.insert(new Review(reviewBody, rating, foodStall, user));
        mongoTemplate.update(FoodStall.class)
                .matching(Query.query(Criteria.where("serialno").is(foodStallSerialno)))
                .apply(Update.update("reviews", new Document("$push", new Document("reviews", review))))
                .first();
        mongoTemplate.update(User.class)
                .matching(Query.query(Criteria.where("username").is(userName)))
                .apply(Update.update("reviews", new Document("$push", new Document("reviews", review))))
                .first();
        return review;
    }
}
