import React, { useState } from "react";
import axios from "axios";
import { Card, Form, Button } from "react-bootstrap";
import "./EditReview.css";
import RatingStars from "react-rating-stars-component";

export default function EditHawkerCentreReview() {
    const userName = sessionStorage.getItem("username");
    const hawkerSerialno = sessionStorage.getItem("hawkerID");
    const hawkerName = sessionStorage.getItem("hawkerName");
    const [reviewBody, setReviewBody] = useState("");
    const [rating, setRating] = useState(0);

    const handleSubmit = (event) => {
        console.log("username: " + userName);
        console.log("hawkerID: " + hawkerSerialno);
        console.log("hawkerName: " + hawkerName);
        console.log("review: " + reviewBody);
        console.log("rating: " + rating);

        event.preventDefault();
        axios
            .post("http://localhost:8080/api/v1/reviews/create/hawker", {
                userName,
                hawkerSerialno,
                hawkerName,
                reviewBody,
                rating,
            })
            .then((response) => {
                console.log("Review created successfully:", response.data);
                alert("Review created successfully");
                window.history.back();
                // You can navigate to another page or show a success message here
            })
            .catch((error) => {
                console.error("Error creating review:", error);
                // You can show an error message to the user here
            });
    };

    const handleRatingChange = (newRating) => {
        setRating(newRating);
    };

    return (
        <div className="review-card-container">
            <Card className="enter-review-card">
                <h1>Edit Hawker Centre Review</h1>
                <h3>Hawker Name: {hawkerName}</h3>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="rating">
                        <Form.Label>Rating (out of 5):</Form.Label>
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
                    <Form.Group controlId="review">
                        <Form.Label>Review:</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows={3}
                            value={reviewBody}
                            onChange={(e) => setReviewBody(e.target.value)}
                        />
                    </Form.Group>
                    <Button type="submit" className="submit-btn">
                        Submit
                    </Button>
                </Form>
            </Card>
        </div>
    );
}
