package com.sc2006.hawker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    private ObjectId id;
    private integer rating;

    public Rating(integer rating) {
        this.rating = ratingBody;
    }
}
