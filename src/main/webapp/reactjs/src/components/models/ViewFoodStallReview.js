import React, {useState} from "react";
import axios from "axios";
import SingleReviewCard from "./SingleReviewCard";

export default function ViewFoodStallReview() {
    const hawkerID = sessionStorage.getItem("hawkerID");
    const hawkerName = sessionStorage.getItem("hawkerName");
    const foodStallID = sessionStorage.getItem("foodStallID");
    const foodStallName = sessionStorage.getItem("foodStallName");

    const [reviews, setReviews] = useState(null);
    axios.get('http://localhost:8080/api/v1/reviews/foodstallfromhakwer/' + hawkerID + "/" + foodStallID)
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