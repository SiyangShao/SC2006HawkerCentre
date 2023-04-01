// import React from "react";
// import {Card, Col} from "react-bootstrap";
//
// import "./SingleReviewCard.css"
//
// export default function SingleReviewCard(review) {
//     const {serialNo, body, rating, userName} = review;
//     const stars = "★".repeat(Math.floor(rating));
//     return (<Col key={serialNo} className="review-list">
//         <Card className="review-card">
//             <div className="review-card-header">
//                 <div className="review-card-body">
//                     {body}
//                 </div>
//                 <div className="review-card-rating">
// <span className="star" data-rating={rating}>
// {stars}
// </span>
//                     <span className="rating-text">{rating} stars</span>
//                 </div>
//             </div>
//
//             <div className="review-card-username">
//                 <span className="username-text">{userName}</span>
//             </div>
//         </Card>
//     </Col>)
// }
import React from "react";
import {Card, Col} from "react-bootstrap";

import "./SingleReviewCard.css"

export default function SingleReviewCard(review) {
    const {serialNo, body, rating, userName} = review;
    const stars = "★".repeat(Math.floor(rating));
    return (<Col key={serialNo} className="review-list">
        <Card className="review-card">
            <div className="review-card-header">
                <div className="review-card-body">
                    {body}
                </div>
                <div className="review-card-rating">
<span className="star" data-rating={rating}>
{stars}
</span>
                    <span className="rating-text">{rating} stars</span>
                </div>
            </div>

            <div className="review-card-username">
                <span className="username-divider">——</span>
                <span className="username-text">{userName}</span>
            </div>
        </Card>
    </Col>)
}