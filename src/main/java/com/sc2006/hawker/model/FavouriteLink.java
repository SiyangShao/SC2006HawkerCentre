package com.sc2006.hawker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "favouritelinks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavouriteLink {
    @Id
    private ObjectId id;

    private String username;
    private String hawkerserialno;
    private String foodstallserialno;

    public FavouriteLink(String username, String hawkerserialno, String foodstallserialno) {
        this.username = username;
        this.hawkerserialno = hawkerserialno;
        this.foodstallserialno = foodstallserialno;
    }
}
