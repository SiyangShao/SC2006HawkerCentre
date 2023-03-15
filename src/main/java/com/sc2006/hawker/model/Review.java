package com.sc2006.hawker.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    private ObjectId id;
    private String body;
    private Integer rating;
    /**
     * The hawker that the review is about.
     */
    @DocumentReference
    private Hawker hawker;

    @DocumentReference
    private FoodStall foodStall;

    public Review(String body, Integer rating, Hawker hawker, User user) {
        this.body = body;
        this.rating = rating;
        this.hawker = hawker;
        this.user = user;
    }

    public Review(String body, Integer rating, FoodStall foodStall, User user) {
        this.body = body;
        this.rating = rating;
        this.foodStall = foodStall;
        this.user = user;
    }

    /**
     * The user who wrote the review.
     */
    @DocumentReference
    private User user;
}
