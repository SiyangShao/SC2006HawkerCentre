import React from "react";

import {Col, Card, ListGroup} from "react-bootstrap";

import "./SingleStallCard.css"
import EditReviewButton from "../reviews/EditReviewButton";
import EditReviewButtonForFoodStall from "../reviews/EditReviewButtonForFoodStall";
import ViewFoodStallReviewButton from "../reviews/ViewFoodStallReviewButton";

let checkTime = (foodstalls, currentDate) => {
    let time = currentDate.getHours() + currentDate.getMinutes()/60;
    let start = foodstalls.openingHours;
    let end = foodstalls.closingHours;
    if (time >= start && time <= end ){
        return "OPEN";
    }
    else return "CLOSED";
};
export const singleStallCard = function (foodstall, currentDate, hawkerID, hawkerName) {
    if (foodstall.stallnumber === undefined) {
        console.log("Food stall number undefined!!! " + foodstall);
    }


    return (
        <Col key={foodstall.stallnumber}>
            <Card className="stall-card">
                <Card.Img variant="top" src={foodstall.photourl} style={{width: "auto", height: 200}}/>
                <Card.Body>
                    <Card.Title>{foodstall.name}</Card.Title>
                    <Card.Text>#{foodstall.stallnumber}</Card.Text>
                    <Card.Text>Status: {checkTime(foodstall, currentDate)}</Card.Text>
                </Card.Body>
                <ListGroup>
                    <ListGroup.Item>
                        <Card.Text>{foodstall.description}</Card.Text>
                    </ListGroup.Item>
                </ListGroup>
                <Card.Footer>
                    <EditReviewButtonForFoodStall hawkerID={hawkerID}
                                                  hawkerName={hawkerName}
                                                  foodStallID={foodstall.stallnumber}
                                                  foodStallName={foodstall.name}/>
                    <ViewFoodStallReviewButton hawkerID={hawkerID}
                                               hawkerName={hawkerName}
                                               foodStallID={foodstall.stallnumber}
                                               foodStallName={foodstall.name}/>
                </Card.Footer>
            </Card>
        </Col>
    );

};