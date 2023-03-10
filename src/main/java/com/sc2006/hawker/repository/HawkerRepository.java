package com.sc2006.hawker.repository;

import com.sc2006.hawker.model.Hawker;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HawkerRepository extends MongoRepository<Hawker, ObjectId> { //<{name of class, type of primary key}>
    Optional<Hawker> findHawkerBySerialno(String serial_no);

    @Query(value = "{'name': {$regex: ?0, $options: 'i'}}")
    List<Hawker> findHawkerByNameRegex(String name);

//    List<Hawker> findHawkerByPostalCode(String postal){

//    }
}


