import React from "react";

import {Button, Col, Card, ListGroup} from "react-bootstrap";

import "./SingleStallCard.css"
import EditReviewButton from "./EditReviewButton";
import EditReviewButtonForFoodStall from "./EditReviewButtonForFoodStall";
import ViewFoodStallReviewButton from "./ViewFoodStallReviewButton";


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
                </Card.Body>
                <ListGroup>
                    <ListGroup.Item>
                        <Card.Text>{foodstall.openingHours}</Card.Text>
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