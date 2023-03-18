package com.sc2006.hawker.repository;

import com.sc2006.hawker.model.Hawker;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@Repository
public interface HawkerRepository extends MongoRepository<Hawker, ObjectId> { //<{name of class, type of primary key}>
    Optional<Hawker> findHawkerBySerialno(String serial_no);

    @Query(value = "{'name': {$regex: ?0, $options: 'i'}}")
    Page<Hawker> findHawkerByNameRegex(String name, Pageable pageable);

    @Query(value = "{'name': {$regex: ?0, $options: 'i'}}")
    Page<Hawker> findAll(String name, Pageable pageable);

}


