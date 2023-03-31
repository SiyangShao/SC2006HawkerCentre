import React from "react";

import {Button, Col, Card, ListGroup} from "react-bootstrap";

import "./SingleHawkerCard.css"

let getDates = (hawkers, quarter) => {
    let q1 = `${hawkers.q1_cleaningstartdate}`+" - "+`${hawkers.q1_cleaningenddate}`
    let q2 = `${hawkers.q2_cleaningstartdate}`+" - "+`${hawkers.q2_cleaningenddate}`
    let q3 = `${hawkers.q3_cleaningstartdate}`+" - "+`${hawkers.q3_cleaningenddate}`
    let q4 = `${hawkers.q4_cleaningstartdate}`+" - "+`${hawkers.q4_cleaningenddate}`
    const allCleanDates = [q1,q2,q3,q4]

    if (allCleanDates[quarter-1] === "TBC - TBC")
        return "-"

    else
        return allCleanDates[quarter-1]
};


export const singleHawkerCard = function (hawker, currentQuarter) {
    if (hawker.serialno === undefined) {
        console.log("Hawker Serialno undefined!!! " + hawker);
    }
    return (
        <Col key={hawker.serialno}>
            <Card>
                <Card.Img variant="top" src={hawker.photourl}/>
                <Card.Body>
                    <Card.Title>{hawker.name}</Card.Title>
                    <Card.Text>{hawker.description_myenv}</Card.Text>
                </Card.Body>
                <ListGroup>
                    <ListGroup.Item>
                        {getDates(hawker, currentQuarter) === '-'
                            ? <Card.Text>No Closing Date!</Card.Text>
                            : <div>
                                <Card.Text>Dates Closed for Cleaning</Card.Text>
                                {getDates(hawker, currentQuarter)}
                            </div>}
                    </ListGroup.Item>
                </ListGroup>
                <Card.Footer>
                    <Button variant="primary">View Food Stalls</Button>
                </Card.Footer>
            </Card>
        </Col>
    );
};