import React, {Component} from 'react';

import "./Nearest.css"

import {Row, Container, InputGroup, FormControl, Button} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSearch, faTimes, faUtensils} from '@fortawesome/free-solid-svg-icons';

import axios from 'axios';
import {singleHawkerCard} from "./SingleHawkerCard";



export default class Nearest extends Component {

    constructor(props) {
        super(props);
        this.state = {
            hawkers: [],
            search: ''
        };
    }

    // function to get a single page from backend
    searchNearest = () => {
        axios.get("http://localhost:8080/api/v1/hawkers/search/nearest?postalcode=" +this.state.search)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    hawkers: data
                })
            });
    };

    // function for searching change for the search filter
    searchChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        })
    };


    // function to cancel search and revert back to first page
    cancelSearch = () => {
        this.setState({"search": ''});
        this.findAllHawkers(1);
    };


    //renders the whole HawkerList page
    render() {
        const {hawkers, search} = this.state;
        const currentDate = new Date();

        return (
            <Container className="my-container">
                <div style={{display: "flex", justifyContent: "center", alignItems: "center"}}><h3>Look for the nearest Hawkers based on postal code!</h3></div>
                <div style={{display: "flex", justifyContent: "center", alignItems: "center"}}>
                    <InputGroup style={{width: "25%"}} size="sm" className="search-bar">
                        <FormControl
                            placeholder="Enter postal code here!"
                            name="search"
                            value={search}
                            className={"info-border"}
                            onChange={this.searchChange}
                            onKeyPress={(event) => {
                                if (event.key === "Enter") {
                                    this.searchNearest();
                                }
                            }}
                        />
                        <Button
                            size="sm"
                            variant="outline-info"
                            type="button"
                            onClick={() => this.searchNearest()}
                        >
                            <FontAwesomeIcon icon={faSearch}/>
                        </Button>
                        <Button
                            size="sm"
                            variant="outline-danger"
                            type="button"
                            onClick={() => this.cancelSearch(1)}
                        >
                            <FontAwesomeIcon icon={faTimes}/>
                        </Button>
                    </InputGroup>
                </div>
                <Row xs={1} md={3} className="g-4">
                    {hawkers.map(hawker => singleHawkerCard(hawker, currentDate))}
                </Row>
            </Container>
        )
            ;
    }
}
