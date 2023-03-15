package com.sc2006.hawker.controller;

import com.sc2006.hawker.service.FoodStallService;
import com.sc2006.hawker.model.FoodStall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/foodstalls") //This mapping will be handled by this controller
public class FoodStallController {
    @Autowired
    private FoodStallService foodstallservice;

    //Returns ALL food stalls in the database. Not used.
    @GetMapping
    public ResponseEntity<List<FoodStall>> getAllFoodStalls(){
        return new ResponseEntity<List<FoodStall>>(foodstallservice.allFoodStalls(), HttpStatus.OK);
    }

    //Returns all food stalls in the hawker centre. Provide fs_serial_no according to hawker centre
    @GetMapping("/{fs_serial_no}")
    public ResponseEntity<List<FoodStall>> getFoodStallList(@PathVariable String fs_serial_no){
        return new ResponseEntity<List<FoodStall>>(foodstallservice.ListOfFoodStalls(fs_serial_no), HttpStatus.OK);
    }

    //Returns an individual food stall.
    @GetMapping("/{fs_serial_no}/{stall_number}")
    public ResponseEntity<Optional<FoodStall>> getSingleFoodStall(@PathVariable String fs_serial_no, @PathVariable String stall_number){
        return new ResponseEntity<Optional<FoodStall>>(foodstallservice.singleFoodStall(fs_serial_no, stall_number), HttpStatus.OK);
    }
}