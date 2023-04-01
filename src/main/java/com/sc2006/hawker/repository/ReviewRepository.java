package com.sc2006.hawker.repository;

import com.sc2006.hawker.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    Optional<Review> findBySerialNo(String serialNo);
    List<Review> findReviewByFoodStallSerialNo(String foodStallSerialNo);
    List<Review> findReviewByHawkerSerialNo(String hawkerSerialNo);
    List<Review> findReviewByHawkerSerialNoAndFoodStallSerialNo(String hawkerSerialNo, String foodStallSerialNo);

    Optional<Review> findReviewByUserNameAndFoodStallSerialNo(String userName, String foodStallSerialno);

    Optional<Review> findReviewByUserNameAndHawkerSerialNo(String userName, String hawkerSerialno);
}
