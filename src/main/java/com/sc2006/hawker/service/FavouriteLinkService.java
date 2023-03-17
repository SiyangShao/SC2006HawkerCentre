package com.sc2006.hawker.service;

import com.sc2006.hawker.model.FavouriteLink;
import com.sc2006.hawker.repository.FavouriteLinkRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FavouriteLinkService {
    @Autowired
    private final FavouriteLinkRepository favouritelinkrepository;

    @Autowired
    private final MongoTemplate mongoTemplate;

    public int getNumberOfFavouriteForUser(String username) {
        return favouritelinkrepository.countFavouriteLinkByUsername(username);
    }

    public List<FavouriteLink> getListOfFavouriteForUser(String username) {
        return favouritelinkrepository.findFavouriteLinkByUsername(username);
    }

    public FavouriteLink addFavouriteLinkToHawker(String username, String serialno) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsernameAndHawkerserialno(username, serialno);
        if (favouritelink.size() == 0) {
            FavouriteLink newFavouriteLink = new FavouriteLink(username, serialno, null);
            return favouritelinkrepository.insert(newFavouriteLink);
        } else {
            throw new IllegalStateException("Favourite link already exists");
        }
    }

    public FavouriteLink addFavouriteLinkToFoodStall(String username, String serialno) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsernameAndFoodstallserialno(username, serialno);
        if (favouritelink.size() == 0) {
            FavouriteLink newFavouriteLink = new FavouriteLink(username, null, serialno);
            return favouritelinkrepository.insert(newFavouriteLink);
        } else {
            throw new IllegalStateException("Favourite link already exists");
        }
    }

    public FavouriteLink deleteFavouriteLinkToHawker(String username, String serialno) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsernameAndHawkerserialno(username, serialno);
        if (favouritelink.size() == 0) {
            throw new IllegalStateException("Favourite link does not exist");
        } else {
            FavouriteLink favouriteLinkToDelete = favouritelink.get(0);
            favouritelinkrepository.delete(favouriteLinkToDelete);
            return favouriteLinkToDelete;
        }
    }

    public FavouriteLink deleteFavouriteLinkToFoodStall(String username, String serialno) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsernameAndFoodstallserialno(username, serialno);
        if (favouritelink.size() == 0) {
            throw new IllegalStateException("Favourite link does not exist");
        } else {
            FavouriteLink favouriteLinkToDelete = favouritelink.get(0);
            favouritelinkrepository.delete(favouriteLinkToDelete);
            return favouriteLinkToDelete;
        }
    }

    public boolean checkFavouriteLinkToHawker(String username, String serialno) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsernameAndHawkerserialno(username, serialno);
        return favouritelink.size() != 0;
    }

    public boolean checkFavouriteLinkToFoodStall(String username, String serialno) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsernameAndFoodstallserialno(username, serialno);
        return favouritelink.size() != 0;
    }

    public int getNumberOfFavouriteForHawker(String username) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsername(username);
        int count = 0;
        for (FavouriteLink favouriteLink : favouritelink) {
            if (favouriteLink.getHawkerserialno() != null) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfFavouriteForFoodStall(String username) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsername(username);
        int count = 0;
        for (FavouriteLink favouriteLink : favouritelink) {
            if (favouriteLink.getFoodstallserialno() != null) {
                count++;
            }
        }
        return count;
    }

    public List<FavouriteLink> getListOfFavouriteForHawker(String username) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsername(username);
        List<FavouriteLink> favouritelinkForHawker = new ArrayList<>();
        for (FavouriteLink favouriteLink : favouritelink) {
            if (favouriteLink.getHawkerserialno() != null) {
                favouritelinkForHawker.add(favouriteLink);
            }
        }
        return favouritelinkForHawker;
    }

    public List<FavouriteLink> getListOfFavouriteForFoodStall(String username) {
        List<FavouriteLink> favouritelink = favouritelinkrepository.findFavouriteLinkByUsername(username);
        List<FavouriteLink> favouritelinkForFoodStall = new ArrayList<>();
        for (FavouriteLink favouriteLink : favouritelink) {
            if (favouriteLink.getFoodstallserialno() != null) {
                favouritelinkForFoodStall.add(favouriteLink);
            }
        }
        return favouritelinkForFoodStall;
    }
}
