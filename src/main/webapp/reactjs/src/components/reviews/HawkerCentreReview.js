import axios from "axios";
import React, {useState} from "react";
import SingleReviewCard from "../hawker/SingleReviewCard";

import "../hawker/FoodStallList.css"

export default function HawkerReview({hawkerid}) {
    const [reviews, setReviews] = useState(null);
    console.log("ID is" + hawkerid.toString());
    axios.get('http://localhost:8080/api/v1/reviews/hawker/' + hawkerid  )
        .then(response => {
            setReviews(response.data)
            // const reviews = response.data;
            console.log(response.data);
            // do something with the reviews data here
        })
        .catch(error => {
            console.error(error);
            // handle any errors here
        });
    // if reviews is null or empty

    if (reviews === null || reviews.length === 0) {
        return (
            <div>
                No Reviews Yet
            </div>
        )
    } else {
        return (
            <div className="review-scrolling-wrapper-flexbox">
                {reviews.map(review => SingleReviewCard(review))}
            </div>
        )
    }
}