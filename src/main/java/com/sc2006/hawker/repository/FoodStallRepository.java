package com.sc2006.hawker.repository;

import com.sc2006.hawker.model.FoodStall;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodStallRepository extends MongoRepository<FoodStall, ObjectId> {
    // Returns the list of food stalls pertaining to the specific hawker centre
    List<FoodStall> findFoodStallByFsserialno(String fsserialno);

    // Returns individual food stall
    Optional<FoodStall> findFoodStallByFsserialnoAndStallnumber(String fsserialno, String stallnumber);

    Optional<FoodStall> findByFsserialno(String fsserialno);
}