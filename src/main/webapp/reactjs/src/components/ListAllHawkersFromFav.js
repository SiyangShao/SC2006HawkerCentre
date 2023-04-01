import React, {useEffect, useState} from "react";
import axios from "axios";
import {Container, Row} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUtensils} from "@fortawesome/free-solid-svg-icons";
import SingleHawkerCard from "./models/SingleHawkerCard";

export default function ListAllHawkersFromFav({hawkerIDs}) {
    const [hawkers, setHawkers] = useState([]);
    useEffect(() => {
        axios.put(`http://localhost:8080/api/v1/hawkers/fromfav`, hawkerIDs)
            .then(response => {
                setHawkers(response.data);
            }).catch(error => {
            console.error(error);
        })
    });
    console.log(hawkers);
    const username = sessionStorage.getItem('username');
    const currentDate = new Date();

    return (
        <Container className="my-container">
            <div style={{display: "flex", justifyContent: "space-between", alignItems: "center"}}>
                <h3 style={{display: "flex", alignSelf: "flex-start"}}><FontAwesomeIcon icon={faUtensils}/> ♡List
                    of Favourite Hawkers</h3>
            </div>
            {/*/!*<GoogleMap/>*!/*/}
            <Row xs={1} md={3} className="g-4">
                {hawkers.map(hawker => <SingleHawkerCard hawker={hawker} currentDate={currentDate}/>)}
            </Row>

        </Container>
    )
}