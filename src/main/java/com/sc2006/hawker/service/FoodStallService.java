package com.sc2006.hawker.service;

import com.sc2006.hawker.model.FoodStall;
import com.sc2006.hawker.repository.FoodStallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodStallService {
    @Autowired
    private FoodStallRepository foodstallrepository;

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

}
