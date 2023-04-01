package com.sc2006.hawker.service;

import com.sc2006.hawker.model.FoodStall;
import com.sc2006.hawker.model.Hawker;
import com.sc2006.hawker.model.Review;
import com.sc2006.hawker.model.User;
import com.sc2006.hawker.repository.ReviewRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * Get Serial No
     */
    public String getSerialNo(String userName, String hawkerSerialNo, String foodStallSerialNo) {
        return userName + "@@@" + hawkerSerialNo + "###" + foodStallSerialNo;
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
        String foodStoreSerialNoForHawker = hawkerSerialno + "###" + "19260817";
        if (reviewRepository.findReviewByUserNameAndFoodStallSerialNo(userName, foodStoreSerialNoForHawker).isPresent()) {
            String serialNo = getSerialNo(userName, hawkerSerialno, foodStoreSerialNoForHawker);
            updateReviewBySerialNo(serialNo, reviewBody, rating);
            Optional<Review> newReview = reviewRepository.findBySerialNo(serialNo);
            if (newReview.isPresent()) {
                return newReview.get();
            } else {
                throw new IllegalArgumentException("Review not found");
            }
        }
        Review review = reviewRepository.insert(new Review(reviewBody, rating, hawkerSerialno, foodStoreSerialNoForHawker, userName));
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
     * @param hawkerSerialNo    serial number of hawker
     * @param foodStallSerialno food stall serial number
     * @param userName          name of user
     * @return review
     */
    public Review createReviewWithFoodStall(String reviewBody, Integer rating, String hawkerSerialNo, String foodStallSerialno, String userName) {
//        if (!hawkerSerialNo.equals(Query.query(Criteria.where("serialno").is(foodStallSerialno)).toString())) {
//            throw new IllegalArgumentException("Hawker serial number does not match food stall serial number");
//        }
        if (reviewRepository.findReviewByUserNameAndFoodStallSerialNo(userName, foodStallSerialno).isPresent()) {
            String serialNo = getSerialNo(userName, hawkerSerialNo, foodStallSerialno);
            updateReviewBySerialNo(serialNo, reviewBody, rating);
            Optional<Review> newReview = reviewRepository.findBySerialNo(serialNo);
            if (newReview.isPresent()) {
                return newReview.get();
            } else {
                throw new IllegalArgumentException("Review not found");
            }
        }
        Review review = reviewRepository.insert(new Review(reviewBody, rating, hawkerSerialNo, foodStallSerialno, userName));
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

    /**
     * Get reviews by hawker serial number
     *
     * @param hawkerSerialNo hawker serial number
     * @return list of reviews
     */
    public List<Review> getReviewsByHawkerSerialNo(String hawkerSerialNo) {
        return mongoTemplate.find(Query.query(Criteria.where("hawkerSerialNo").is(hawkerSerialNo)), Review.class);
    }

    /**
     * Get reviews by food stall serial number
     *
     * @param foodStallSerialNo food stall serial number
     * @return list of reviews
     */
    public List<Review> getReviewsByFoodStallSerialNo(String foodStallSerialNo) {
        return mongoTemplate.find(Query.query(Criteria.where("foodStallSerialNo").is(foodStallSerialNo)), Review.class);
    }

    /**
     * Get reviews by user name
     *
     * @param userName user name
     * @return list of reviews
     */
    public List<Review> getReviewsByUserName(String userName) {
        return mongoTemplate.find(Query.query(Criteria.where("userName").is(userName)), Review.class);
    }

    /**
     * Delete review by serial number
     *
     * @param serialNo serial number
     * @return review
     */
    public Optional<Review> deleteReviewBySerialNo(String serialNo) {
        Optional<Review> review = reviewRepository.findBySerialNo(serialNo);
        if (review.isPresent()) {
            System.err.println("Found! " + review.get().getFoodStallSerialNo());
            reviewRepository.delete(review.get());
            mongoTemplate.update(User.class)
                    .matching(Query.query(Criteria.where("username").is(review.get().getUserName())))
                    .apply(new Update().pull("reviews", new Document("serialNo", serialNo)))
                    .first();
            if (!review.get().getFoodStallSerialNo().isEmpty()) {
                mongoTemplate.update(FoodStall.class)
                        .matching(Query.query(Criteria.where("serialno").is(review.get().getFoodStallSerialNo())))
                        .apply(new Update().pull("reviews", new Document("serialNo", serialNo)))
                        .first();
            }
            if (!review.get().getHawkerSerialNo().isEmpty()) {
                mongoTemplate.update(Hawker.class)
                        .matching(Query.query(Criteria.where("serialno").is(review.get().getHawkerSerialNo())))
                        .apply(new Update().pull("reviews", new Document("serialNo", serialNo)))
                        .first();
            }
            return review;
        }
        return Optional.empty();
    }

    /**
     * Delete all reviews by food stall serial number
     *
     * @param foodstoreSerialNo food stall serial number
     * @return deletedReviews deleted reviews
     */
    public List<Optional<Review>> deleteAllReviewByFoodStallSerialNo(String foodstoreSerialNo) {
        List<Review> reviews = reviewRepository.findReviewByFoodStallSerialNo(foodstoreSerialNo);
        List<Optional<Review>> deletedReviews = new ArrayList<>();
        for (Review review : reviews) {
            deletedReviews.add(deleteReviewBySerialNo(review.getSerialNo()));
        }
        return deletedReviews;
    }

    /**
     * Delete all reviews by hawker serial number
     *
     * @param hawkerSerialNo hawker serial number
     * @return deletedReviews deleted reviews
     */
    public List<Optional<Review>> deleteAllReviewByHawkerSerialNo(String hawkerSerialNo) {
        List<Review> reviews = reviewRepository.findReviewByHawkerSerialNo(hawkerSerialNo);
        List<Optional<Review>> deletedReviews = new ArrayList<>();
        for (Review review : reviews) {
            deletedReviews.add(deleteReviewBySerialNo(review.getSerialNo()));
        }
        return deletedReviews;
    }

    /**
     * Update review by serial number
     *
     * @param serialNo   serial number
     * @param reviewBody review body
     * @param rating     rating
     * @return review
     */
    public Optional<Review> updateReviewBySerialNo(String serialNo, String reviewBody, Integer rating) {
        Optional<Review> review = reviewRepository.findBySerialNo(serialNo);
        if (review.isPresent()) {
            review.get().setBody(reviewBody);
            review.get().setRating(rating);
            reviewRepository.save(review.get());
            return review;
        }
        return Optional.empty();
    }

    /**
     * Get hawker rating
     *
     * @param hawkerSerialNo hawker serial number
     * @return rating
     */
    public double getHawkerRating(String hawkerSerialNo) {
        List<Review> reviews = getReviewsByHawkerSerialNo(hawkerSerialNo);
        double total = 0;
        for (Review review : reviews) {
            total += review.getRating();
        }
        return total / reviews.size();
    }

    /**
     * Get food stall rating
     *
     * @param foodStallSerialNo food stall serial number
     * @return rating
     */
    public double getFoodStallRating(String foodStallSerialNo) {
        List<Review> reviews = getReviewsByFoodStallSerialNo(foodStallSerialNo);
        double total = 0;
        for (Review review : reviews) {
            total += review.getRating();
        }
        return total / reviews.size();
    }

    public Optional<Review> getReviewByUserNameAndFoodStallSerialNo(String userName, String foodStallSerialno) {
        return reviewRepository.findReviewByUserNameAndFoodStallSerialNo(userName, foodStallSerialno);
    }

    public Optional<Review> getReviewByUserNameAndHawkerSerialNo(String userName, String hawkerSerialno) {
        return reviewRepository.findReviewByUserNameAndHawkerSerialNo(userName, hawkerSerialno);
    }

    public List<Review> getReviewsByHawkerAndFoodStall(String hawkerserialno, String foodstallserialno) {
        return reviewRepository.findReviewByHawkerSerialNoAndFoodStallSerialNo(hawkerserialno, foodstallserialno);
    }
}
