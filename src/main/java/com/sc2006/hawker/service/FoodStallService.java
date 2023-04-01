package com.sc2006.hawker.service;

import com.sc2006.hawker.model.FoodStall;
import com.sc2006.hawker.repository.FoodStallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FoodStallService {
    @Autowired
    private FoodStallRepository foodstallrepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<FoodStall> allFoodStalls() {
        return foodstallrepository.findAll();
    }

    //Returns all food stalls in a specific hawker centre
    public List<FoodStall> ListOfFoodStalls(String fsserialno) {
        return foodstallrepository.findFoodStallByFsserialno(fsserialno);
    }

    //Returns an individual food stall
    public Optional<FoodStall> singleFoodStall(String fsserialno, String stallnumber) {
        return foodstallrepository.findFoodStallByFsserialnoAndStallnumber(fsserialno, stallnumber);
    }

    public FoodStall addNewFoodStall(FoodStall foodstall) {
        /*
        if (foodstallrepository.findByFsserialno(foodstall.getFsserialno()).isPresent()) {
            throw new RuntimeException("The Fsserialno of the Food Stall already exists");
        } */
        return mongoTemplate.insert(foodstall);
    }

    public void deleteFoodStall(String fsserialno) {
        Optional<FoodStall> foodstall = foodstallrepository.findByFsserialno(fsserialno);
        if (foodstall.isPresent()) {
            foodstallrepository.delete(foodstall.get());
        } else {
            throw new RuntimeException("Food Stall not found");
        }
    }

    public FoodStall updateFoodStall(Map<String, String> item) {
        Optional<FoodStall> foodstall = foodstallrepository.findByFsserialno(item.get("fs_serial_no"));
        if (foodstall.isPresent()) {
            for (String key : item.keySet()) {
                switch (key) {
                    case "name" -> foodstall.get().setName(item.get(key));
                    case "remarks" -> foodstall.get().setRemarks(item.get(key));
                    case "stall_number" -> foodstall.get().setStallnumber(item.get(key));
                    case "description" -> foodstall.get().setDescription(item.get(key));
                    case "opening_hours" -> foodstall.get().setOpeningHours(item.get(key));
                    case "photourl" -> foodstall.get().setPhotourl(item.get(key));
                    case "status" -> foodstall.get().setStatus(item.get(key));
                }
            }
            return foodstallrepository.save(foodstall.get());
        } else {
            throw new RuntimeException("Food Stall not found");
        }
    }
}
