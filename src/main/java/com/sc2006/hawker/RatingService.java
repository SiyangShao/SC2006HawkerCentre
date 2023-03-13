package com.sc2006.hawker.service;

import com.sc2006.hawker.model.Hawker;
import com.sc2006.hawker.model.Rating;
import com.sc2006.hawker.repository.RatingRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final RatingRepository ratingRepository;

    private final MongoTemplate mongoTemplate;

    public RatingService(MongoTemplate mongoTemplate, RatingRepository ratingRepository) {
        this.mongoTemplate = mongoTemplate;
        this.ratingRepository = ratingRepository;
    }

    public Rating createRating(Integer ratingBody, String serialno){
        Rating rating = ratingRepository.insert(new Rating(ratingBody));

        mongoTemplate.update(Hawker.class)
                .matching(Criteria.where("serialno").is(serialno))
                .apply(new Update().push("ratings").value(rating))
                .first();

        return review;
    }

    /**
     * Update rating
     * @param ratingBody - rating body
     * @param serialno - serial number of hawker
     * @return - updated rating
     */
    public Rating updateRating(Integer ratingBody, String serialno) {
        Rating rating = ratingRepository.insert(new Rating(ratingBody));

        mongoTemplate.update(Hawker.class)
                .matching(Criteria.where("serialno").is(serialno))
                .apply(new Update().push("ratings").value(rating))
                .first();

        return rating;
    }

    /**
     * Delete rating
     * @param serialno - serial number of hawker
     * @param id - id of rating
     * @return - deleted rating
     */
    public Rating deleteRating(String serialno, ObjectId id) {
        Rating rating = ratingRepository.findById(id).get();

        mongoTemplate.update(Hawker.class)
                .matching(Criteria.where("serialno").is(serialno))
                .apply(new Update().pull("ratings", Query.query(Criteria.where("id").is(rating.getId()))))
                .first();

        return rating;
    }
}
