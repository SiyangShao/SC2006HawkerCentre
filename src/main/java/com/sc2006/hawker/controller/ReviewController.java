package com.sc2006.hawker.controller;


import com.sc2006.hawker.model.Review;
import com.sc2006.hawker.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/create/hawker")
    public ResponseEntity<Review> createReviewWithHawker(@RequestBody Map<String, String> body) {
        String reviewBody = body.get("reviewBody");
        Integer rating = Integer.parseInt(body.get("rating"));
        String hawkerSerialno = body.get("hawkerSerialno");
        String userName = body.get("userName");
//        System.out.println(reviewBody + "\n" + rating + "\n" + hawkerSerialno + "\n" + userName);
        return new ResponseEntity<>(reviewService.createReviewWithHawker(reviewBody, rating, hawkerSerialno, userName), HttpStatus.CREATED);
    }

    /**
     * Get all reviews
     */
    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    /**
     * Get review by serial number
     *
     * @param serialNo serial number
     * @return review
     */
    @GetMapping("/serialno/{serialNo}")
    public ResponseEntity<Optional<Review>> getReviewBySerialNo(@PathVariable String serialNo) {
        return new ResponseEntity<>(reviewService.getReviewBySerialNo(serialNo), HttpStatus.OK);
    }

}
