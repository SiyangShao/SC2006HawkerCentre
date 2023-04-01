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
    private String serialNo;
    private String body;
    private Integer rating;
    /**
     * The hawker that the review is about.
     */
    private String hawkerSerialNo;

    private String foodStallSerialNo;

    private void setSerialNo() {
        this.serialNo = this.userName + "@@@" + this.hawkerSerialNo + "###" + this.foodStallSerialNo;
    }

    public Review(String body, Integer rating, String hawkerSerialNo, String foodStallSerialNo, String userName) {
        this.body = body;
        this.rating = rating;
        this.hawkerSerialNo = hawkerSerialNo;
        this.foodStallSerialNo = foodStallSerialNo;
        this.userName = userName;
        setSerialNo();
    }

    /**
     * The user who wrote the review.
     */
    private String userName;

    public void setReviewBody(String reviewBody) {
        this.body = reviewBody;
    }
}
