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

    /**
     * Create a review with hawker
     * @param body review body
     * @return review
     */
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
     * Create a review with food stall
     * @param body review body
     * @return review
     */
    @PostMapping("/create/foodstall")
    public ResponseEntity<Review> createReviewWithFoodStall(@RequestBody Map<String, String> body) {
        String reviewBody = body.get("reviewBody");
        Integer rating = Integer.parseInt(body.get("rating"));
        String foodStallSerialno = body.get("foodStallSerialno");
        String userName = body.get("userName");
        return new ResponseEntity<>(reviewService.createReviewWithFoodStall(reviewBody, rating, foodStallSerialno, userName), HttpStatus.CREATED);
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

    /**
     * Get reviews by hawker serial number
     *
     * @param hawkerSerialNo serial number of hawker
     * @return reviews
     */
    @GetMapping("/hawker/{hawkerSerialNo}")
    public ResponseEntity<List<Review>> getReviewsByHawkerSerialNo(@PathVariable String hawkerSerialNo) {
        return new ResponseEntity<>(reviewService.getReviewsByHawkerSerialNo(hawkerSerialNo), HttpStatus.OK);
    }

    /**
     * Get reviews by food stall serial number
     *
     * @param foodStallSerialNo serial number of food stall
     * @return reviews
     */
    @GetMapping("/foodstall/{foodStallSerialNo}")
    public ResponseEntity<List<Review>> getReviewsByFoodStallSerialNo(@PathVariable String foodStallSerialNo) {
        return new ResponseEntity<>(reviewService.getReviewsByFoodStallSerialNo(foodStallSerialNo), HttpStatus.OK);
    }

    /**
     * Get reviews by user name
     *
     * @param userName user name
     * @return reviews
     */
    @GetMapping("/user/{userName}")
    public ResponseEntity<List<Review>> getReviewsByUserName(@PathVariable String userName) {
        return new ResponseEntity<>(reviewService.getReviewsByUserName(userName), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<Review>> deleteReviewBySerialNo(@RequestBody Map<String, String> body) {
        String serialNo = body.get("serialNo");
        return new ResponseEntity<>(reviewService.deleteReviewBySerialNo(serialNo), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Optional<Review>> updateReviewBySerialNo(@RequestBody Map<String, String> body) {
        String serialNo = body.get("serialNo");
        String reviewBody = body.get("reviewBody");
        Integer rating = Integer.parseInt(body.get("rating"));
        return new ResponseEntity<>(reviewService.updateReviewBySerialNo(serialNo, reviewBody, rating), HttpStatus.OK);
    }

}
