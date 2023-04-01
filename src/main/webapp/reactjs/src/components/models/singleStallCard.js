import React from "react";

import {Button, Col, Card, ListGroup} from "react-bootstrap";

import "./SingleStallCard.css"


export const singleStallCard = function (foodstall, currentDate) {
    if (foodstall.stallnumber === undefined) {
        console.log("Food stall number undefined!!! " + foodstall);
    }
    return (
        <Col key={foodstall.stallnumber}>
            <Card className="stall-card">
                <Card.Img variant="top" src={foodstall.photourl} />
                <Card.Body>
                    <Card.Title>{foodstall.name}</Card.Title>
                    <Card.Text>#{foodstall.stallnumber}</Card.Text>
                    <Card.Text>{foodstall.description}</Card.Text>
                </Card.Body>
                <ListGroup>
                    <ListGroup.Item>
                    </ListGroup.Item>
                </ListGroup>
                <Card.Footer>
                </Card.Footer>
            </Card>
        </Col>
    );
};