package com.sc2006.hawker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "foodstalls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodStall {
    @Id
    private ObjectId _id;

    //Each fs_serial_no should correspond to one list of food stalls pertaining to one hawker centre
    //So several food stalls within the same hawker centre will have the same fs_serial_no
    @Field(name = "fs_serial_no")
    private String fsserialno;

    //stall_number refers to the individual stall within each list of food stalls
    @Field(name = "stall_number")
    private String stallnumber;
    private String name;
    private String remarks;
    private String photourl;
    private String description;
    private String openingHours;
    private String status;


}
