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
     * The food stall that the review is about.
     */
    @DocumentReference
    private FoodStall foodStall;
    /**
     * The hawker that the review is about.
     */
    @DocumentReference
    private Hawker hawker;
    /**
     * The user who wrote the review.
     */
    @DocumentReference
    private User user;
}
