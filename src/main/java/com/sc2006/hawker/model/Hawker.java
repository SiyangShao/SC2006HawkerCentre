package com.sc2006.hawker.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


import java.util.List;

@Document(collection = "hawkers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hawker {
    @Id
    private ObjectId _id;
    @Field(name = "serial_no")
    private String serialno;
    private String name;
    private String q1_cleaningstartdate;
    private String q1_cleaningenddate;
    private String remarks_q1;
    private String q2_cleaningstartdate;
    private String q2_cleaningenddate;
    private String remarks_q2;
    private String q3_cleaningstartdate;
    private String q3_cleaningenddate;
    private String remarks_q3;
    private String q4_cleaningstartdate;
    private String q4_cleaningenddate;
    private String remarks_q4;

    private String other_works_startdate;
    private String other_works_enddate;
    private String remarks_other_works;
    private String latitude_hc;
    private String longitude_hc;
    private String photourl;
    private String address_myenv;
    private String no_of_market_stalls;
    private String no_of_food_stalls;
    private String description_myenv;
    private String status;
    private String google_3d_view;
    private String google_for_stall;
    @DocumentReference
    private List<Review> reviews;
}
