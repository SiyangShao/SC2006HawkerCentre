package com.sc2006.hawker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Rating>(ratingService.createRating(payload.get("ratingBody"), payload.get("serialno")), HttpStatus.CREATED);
    }
}
