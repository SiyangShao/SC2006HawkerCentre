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

    List<FavouriteLink> findUniqueFavouriteLink(String username, String hawkerserialno, String foodstallserialno);

    int countFavouriteLinkByUsername(String username);

    int countFavouriteLinkByHawkerserialno(String serialno);

    int countFavouriteLinkByFoodstallserialno(String serialno);

    List<FavouriteLink> findFavouriteLinkByHawkerserialno(String serialno);

    List<FavouriteLink> findFavouriteLinkByFoodstallserialno(String serialno);
}
