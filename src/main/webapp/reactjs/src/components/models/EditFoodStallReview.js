import React, { useState } from "react";
import axios from "axios";
import { Card, Form, Button } from "react-bootstrap";
import RatingStars from "react-rating-stars-component"; // 导入react-rating-stars-component
import "./EditReview.css";

export default function EditFoodStallReview() {
    const userName = sessionStorage.getItem("username");
    const hawkerSerialno = sessionStorage.getItem("hawkerID");
    const hawkerName = sessionStorage.getItem("hawkerName");
    const foodStallSerialno = sessionStorage.getItem("foodStallID");
    const foodStallName = sessionStorage.getItem("foodStallName");
    const [realReviewBody, setRealReviewBody] = useState("");
    const [rating, setRating] = useState("");

    const handleSubmit = (event) => {
        console.log("username: " + userName);
        console.log("hawkerID: " + hawkerSerialno);
        console.log("hawkerName: " + hawkerName);
        console.log("foodStallID: " + foodStallSerialno);
        console.log("foodStallName: " + foodStallName);
        console.log("review: " + realReviewBody);
        console.log("rating: " + rating);
        const reviewBody = realReviewBody + "\nFor food stall: " + foodStallName;
        event.preventDefault();
        axios
            .post("http://localhost:8080/api/v1/reviews/create/foodstall", {
                userName,
                hawkerSerialno,
                foodStallSerialno,
                hawkerName,
                reviewBody,
                rating,
            })
            .then((response) => {
                console.log("Review created successfully:", response.data);
                alert("Review created successfully");
                // go back to previous page
                window.history.back();
                // You can navigate to another page or show a success message here
            })
            .catch((error) => {
                console.error("Error creating review:", error);
                // You can show an error message to the user here
            });
    };

    return (
        <div className="review-card-container">
            <Card className="enter-review-card">
                <h1>Edit Hawker Centre Review</h1>
                <h3>Hawker Name: {hawkerName}</h3>
                <h3>Food Stall Name: {foodStallName}</h3>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="rating">
                        <Form.Label>Rating (out of 5):</Form.Label>
                        {/* 使用react-rating-stars-component替代Form.Control，并添加样式 */}
                        <div style={{ display: "flex", justifyContent: "center" }}>
                            <RatingStars
                                count={5}
                                size={24}
                                activeColor="#ffd700"
                                isHalf={false}
                                value={rating}
                                onChange={setRating}
                            />
                        </div>
                    </Form.Group>
                    <Form.Group controlId="reviewBody">
                        <Form.Label>Review:</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows={4}
                            value={realReviewBody}
                            onChange={(e) => setRealReviewBody(e.target.value)}
                        />
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </Card>
        </div>
    );
}
