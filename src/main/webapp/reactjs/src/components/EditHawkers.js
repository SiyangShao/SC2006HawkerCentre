import React, {Component, useEffect, useState} from 'react';

import "./HawkerList.css"
import "./models/SearchBar.css"

import {Row, Container, InputGroup, FormControl, Button} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSearch, faTimes, faUtensils} from '@fortawesome/free-solid-svg-icons';
import {faStepBackward, faStepForward, faFastForward, faFastBackward} from "@fortawesome/free-solid-svg-icons";

import axios from 'axios';
import SingleHawkerCard from "./models/SingleHawkerCard.js";
import GoogleMap from "./models/GoogleMap.js";

export default function EditHawkers() {


    const status = sessionStorage.getItem("isLoggedIn");
    if (status !== 'true') {
        alert("Please login to view favourite Hawkers!")
        window.location.href = "/login";
    }
    const username = sessionStorage.getItem('username');
    const [hawkerIDs, setHawkerIDs] = useState([]);

    useEffect(() => {
        if (status !== 'true') {
            window.location.href = '/login';
        } else {
            axios
                .get(`http://localhost:8080/api/v1/fav/user/${username}/hawker/list`)
                .then(response => {
                    setHawkerIDs(response.data);
                    console.log(response.data);
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }, [status, username]);
    console.log(hawkerIDs);
    const currentDate = new Date();

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
    return (
        <Container className="my-container">
            <div style={{display: "flex", justifyContent: "space-between", alignItems: "center"}}>
                <h3 style={{display: "flex", alignSelf: "flex-start"}}><FontAwesomeIcon icon={faUtensils}/> List
                    of Hawkers</h3>
            </div>
            {/*/!*<GoogleMap/>*!/*/}
            {/*<Row xs={1} md={3} className="g-4">*/}
            {/*    {hawkers.map(hawker => <SingleHawkerCard hawker={hawker} currentDate={currentDate}/>)}*/}
            {/*</Row>*/}

        </Container>
    )
}
