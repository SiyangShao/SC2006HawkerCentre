package com.sc2006.hawker.controller;


import com.sc2006.hawker.model.Review;
import com.sc2006.hawker.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Optional<Review>> getReview(@PathVariable String id) {
        return new ResponseEntity<Optional<Review>>(reviewService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/movie/reviews")
    public ResponseEntity<Iterable<Review>> getAllReviews() {
        return new ResponseEntity<Iterable<Review>>(reviewService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("serialno")), HttpStatus.CREATED);
    }
}
