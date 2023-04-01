import React,{useState} from "react";

import {Button, Col, Card, ListGroup} from "react-bootstrap";

import "./SingleHawkerCard.css"
import FoodStallList from "../FoodStallList.js";
import GoogleMap from "./GoogleMap.js";

// function for getting quarter # of current date
let currentQuarter = (date) => {
    const currentMonth = date.getMonth();

    let currentQuarter;
    if (currentMonth >= 0 && currentMonth <= 2) {
        currentQuarter = 1;
    } else if (currentMonth >= 3 && currentMonth <= 5) {
        currentQuarter = 2;
    } else if (currentMonth >= 6 && currentMonth <= 8) {
        currentQuarter = 3;
    } else if (currentMonth >= 9 && currentMonth <= 11) {
        currentQuarter = 4;
    }
    return currentQuarter
};

let checkDate = (hawkers, currentDate) => {
        let quarter = currentQuarter(currentDate)
        let q = ["q1_", "q2_", "q3_", "q4_"]
        let sDate = q[quarter-1]+"cleaningstartdate"
        let eDate = q[quarter-1]+"cleaningenddate"
        let start = hawkers[sDate]
        let end = hawkers[eDate]
        if ((start || end) === "TBC"){
            return '-'
        }
        let [eDay, eMonth, eYear] = end.split("/");
        let [sDay, sMonth, sYear] = start.split("/");
        const closingEndDate = new Date(eYear+"-"+eMonth+"-"+eDay)
        const closingStartDate = new Date(sYear+"-"+sMonth+"-"+sDay)

        if (closingEndDate < currentDate){
            return '-'
        }
        else if (closingStartDate < currentDate && currentDate < closingEndDate){
            return `${start}`+" - "+`${end}`
        }
        else{
            return `${start}`+" - "+`${end}`
        }
    };


export default function SingleHawkerCard(props) {
    const {hawker, currentDate} = props;
    const [showMap, setShowMap] = useState(false);

    const handleMapButtonClick = () => {
        setShowMap(true);
    };

    const handleCloseMap = () => {
        setShowMap(false);
    };

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
                        {checkDate(hawker, currentDate) === '-'
                            ? <Card.Text>No Closing Date!</Card.Text>
                            : <div>
                                <Card.Text>Dates Closed for Cleaning</Card.Text>
                                {checkDate(hawker, currentDate)}
                            </div>}
                    </ListGroup.Item>
                </ListGroup>
                <Card.Footer>
                    <Button variant="primary" onClick={handleMapButtonClick}>View Map</Button>
                    <FoodStallList
                        name = {hawker.name}
                        photourl = {hawker.photourl}
                        hawkerserial = {hawker.serialno}
                    />                </Card.Footer>
            </Card>
            {showMap && (
                <div>
                    <div className="map-shadow" onClick={handleCloseMap}/>
                    <div className="map-overlay">
                        <GoogleMap markersData={[{lat: parseFloat(hawker.latitude_hc), lng: parseFloat(hawker.longitude_hc)}]} />
                    </div>
                </div>
            )}
        </Col>
    );
};