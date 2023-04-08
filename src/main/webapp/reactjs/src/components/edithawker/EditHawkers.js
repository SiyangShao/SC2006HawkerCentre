import React, {Component, useEffect, useState} from 'react';

import "../hawkerlist/HawkerList.css"
import "../hawkerlist/SearchBar.css"

import {Row, Container, InputGroup, FormControl, Button} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSearch, faTimes, faUtensils} from '@fortawesome/free-solid-svg-icons';
import {faStepBackward, faStepForward, faFastForward, faFastBackward} from "@fortawesome/free-solid-svg-icons";

import axios from 'axios';
import SingleHawkerCard from "../hawker/SingleHawkerCard.js";
import GoogleMap from "../map/GoogleMap.js";
import ListAllHawkersFromFav from "../fav/ListAllHawkersFromFav";


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

    return (
        <ListAllHawkersFromFav hawkerIDs={hawkerIDs}/>
    );

}
