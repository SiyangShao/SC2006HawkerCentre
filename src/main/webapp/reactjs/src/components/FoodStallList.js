import React, { useEffect, useState} from 'react';

import "./FoodStallList.css"
import HawkerList from "./HawkerList";

import {Row, Container, InputGroup, FormControl, Button, Modal, Card, ListGroup} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSearch, faTimes, faUtensils} from '@fortawesome/free-solid-svg-icons';
import {faStepBackward, faStepForward, faFastForward, faFastBackward} from "@fortawesome/free-solid-svg-icons";

import axios from'axios';



export default function FoodStallList(props){

    const [foodstalls, getFoodstalls] = useState([]);

    //need to wait for data to load from backend or else page will not load
    const [isLoading, setIsLoading] = useState(true);

    //can concat with other foodstall ids once database is updated
    function getAllFoodStalls() {
        axios.get("http://localhost:8080/api/v1/foodstalls/1")
            .then(response => response.data)
            .then((data) => {
               getFoodstalls(data)
            })
    };

    useEffect (() => {
        getAllFoodStalls();
    }, []);


    //modal popup useState
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    //loading screen
    //yes this is necessary
    if (isLoading){
        if (foodstalls[0] !== undefined) setIsLoading(false);
        return <div>Loading...</div>;
    }
    //debugging to check for above if the code ever breaks again
    console.log(isLoading);
    console.log(foodstalls);

    return (
        <>
            <Button variant="primary" onClick={handleShow}>
                View Food Stalls
            </Button>

            <Modal show={show} onHide={handleClose} dialogClassName="my-modal" contentClassName="modal-height">
                <Modal.Header>
                    <Container className="modal-header">
                    <Modal.Title>{props.name}</Modal.Title>
                    <img class="img-size" src={props.photourl}/>
                    </Container>
                </Modal.Header>
                <Modal.Body>
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={foodstalls[0].photourl} />
                        <Card.Body>
                            <Card.Title>{foodstalls[0].name}</Card.Title>
                            <Card.Text>{foodstalls[0].description}</Card.Text>
                        </Card.Body>
                        <ListGroup>
                            <ListGroup.Item>
                            </ListGroup.Item>
                        </ListGroup>
                        <Card.Footer>
                        </Card.Footer>
                    </Card>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>

                </Modal.Footer>
            </Modal>
        </>
    );
}