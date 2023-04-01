import React from "react";
import {Col} from "react-bootstrap";

export default function SingleReviewCard(review) {
    const {serialNo, body, rating, userName} = review;
    return (
        <Col key={serialNo} className="single-review-card">
            <p>
                User: {userName}
            </p>
            <p>
                Rating: {rating}
            </p>
            <p>
                Review: {body}
            </p>
        </Col>
    )
}