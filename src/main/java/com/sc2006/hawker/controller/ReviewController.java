package com.sc2006.hawker.controller;


import com.sc2006.hawker.model.Review;
import com.sc2006.hawker.service.ReviewService;
import org.bson.types.ObjectId;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Review>> getReview(@PathVariable String id) {
        return new ResponseEntity<>(reviewService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> createReviewWithHawker(@RequestBody Map<String, String> body) {
        String reviewBody = body.get("reviewBody");
        Integer rating = Integer.parseInt(body.get("rating"));
        String hawkerSerialno = body.get("hawkerSerialno");
        String userName = body.get("userName");
        System.out.println(reviewBody + "\n" + rating + "\n" + hawkerSerialno + "\n" + userName);
        return new ResponseEntity<>(reviewService.createReviewWithHawker(reviewBody, rating, hawkerSerialno, userName), HttpStatus.CREATED);
    }

}
