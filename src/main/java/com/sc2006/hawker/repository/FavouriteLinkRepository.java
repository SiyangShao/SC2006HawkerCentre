package com.sc2006.hawker.repository;

import com.sc2006.hawker.model.FavouriteLink;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteLinkRepository extends MongoRepository<FavouriteLink, ObjectId> {
    List<FavouriteLink> findFavouriteLinkByUsername(String username);
    List<FavouriteLink> findFavouriteLinkByUsernameAndHawkerserialno(String username, String hawkerserialno);
    List<FavouriteLink> findFavouriteLinkByUsernameAndFoodstallserialno(String username, String foodstallserialno);

    int countFavouriteLinkByUsername(String username);
}
