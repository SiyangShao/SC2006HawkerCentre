package com.sc2006.hawker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    public ObjectId get_id() {
        return _id;
    }

    public String getSerialno() {
        return serialno;
    }

    public String getName() {
        return name;
    }

    public String getQ1_cleaningstartdate() {
        return q1_cleaningstartdate;
    }

    public String getQ1_cleaningenddate() {
        return q1_cleaningenddate;
    }

    public String getRemarks_q1() {
        return remarks_q1;
    }

    public String getQ2_cleaningstartdate() {
        return q2_cleaningstartdate;
    }

    public String getQ2_cleaningenddate() {
        return q2_cleaningenddate;
    }

    public String getRemarks_q2() {
        return remarks_q2;
    }

    public String getQ3_cleaningstartdate() {
        return q3_cleaningstartdate;
    }

    public String getQ3_cleaningenddate() {
        return q3_cleaningenddate;
    }

    public String getRemarks_q3() {
        return remarks_q3;
    }

    public String getQ4_cleaningstartdate() {
        return q4_cleaningstartdate;
    }

    public String getQ4_cleaningenddate() {
        return q4_cleaningenddate;
    }

    public String getRemarks_q4() {
        return remarks_q4;
    }

    public String getOther_works_startdate() {
        return other_works_startdate;
    }

    public String getOther_works_enddate() {
        return other_works_enddate;
    }

    public String getRemarks_other_works() {
        return remarks_other_works;
    }

    public String getLatitude_hc() {
        return latitude_hc;
    }

    public String getLongitude_hc() {
        return longitude_hc;
    }

    public String getPhotourl() {
        return photourl;
    }

    public String getAddress_myenv() {
        return address_myenv;
    }

    public String getNo_of_market_stalls() {
        return no_of_market_stalls;
    }

    public String getNo_of_food_stalls() {
        return no_of_food_stalls;
    }

    public String getDescription_myenv() {
        return description_myenv;
    }

    public String getStatus() {
        return status;
    }

    public String getGoogle_3d_view() {
        return google_3d_view;
    }

    public String getGoogle_for_stall() {
        return google_for_stall;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
