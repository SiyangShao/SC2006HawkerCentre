import React, {Component} from 'react';

import "./HawkerList.css"
import "./models/SearchBar.css"

import {Row, Container, InputGroup, FormControl, Button} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSearch, faTimes, faUtensils} from '@fortawesome/free-solid-svg-icons';
import {faStepBackward, faStepForward, faFastForward, faFastBackward} from "@fortawesome/free-solid-svg-icons";

import axios from 'axios';
import {singleHawkerCard} from "./models/SingleHawkerCard";


export default class HawkerList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            hawkers: [],
            search: '',
            currentPage: 1,
            hawkersPerPage: 9
        };
    }

    componentDidMount() {
        this.findAllHawkers(this.state.currentPage);
    }

    // function to get all hawkers from backend
    findAllHawkers(currentPage) {
        currentPage -= 1
        axios.get("http://localhost:8080/api/v1/hawkersp?page=" + currentPage + "&size=" + this.state.hawkersPerPage)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    hawkers: data.content,
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    currentPage: data.number + 1
                })
            });
    };

    // function to get a single page from backend
    searchData = (currentPage) => {
        currentPage = 0
        axios.get("http://localhost:8080/api/v1/hawkers/search?name=" + this.state.search + "&page=" + currentPage + "&size=" + this.state.hawkersPerPage)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    hawkers: data.content,
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    currentPage: data.number + 1
                })
            });
    };

    // function for page change
    changePage = event => {
        let targetPage = parseInt(event.target.value);
        this.findAllHawkers(targetPage);
        this.setState({
            [event.target.name]: targetPage
        });
    };

    // function for getting firstPage from Hawker List Pagination
    firstPage = () => {
        let firstPage = 1;
        if (this.state.currentPage > 1) {
            this.findAllHawkers(firstPage);
        }
    };

    // function for getting the previous page
    prevPage = () => {
        let prevPage = 1;
        if (this.state.currentPage > 1) {
            this.findAllHawkers(this.state.currentPage - prevPage);
        }
    };

    // function for getting the next page
    nextPage = () => {
        if (this.state.currentPage < Math.ceil(this.state.totalElements / this.state.hawkersPerPage)) {
            this.findAllHawkers(this.state.currentPage + 1)
        }
    };

    // function for getting the last page
    lastPage = () => {
        let condition = Math.ceil(this.state.totalElements / this.state.hawkersPerPage);
        if (this.state.currentPage < condition) {
            this.findAllHawkers(condition);
        }
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
        const {hawkers, currentPage, hawkersPerPage, search} = this.state;
        const totalPages = Math.ceil(this.state.totalElements / hawkersPerPage);
        const currentDate = new Date();

        return (
            <Container className="my-container">
                <div style={{display: "flex", justifyContent: "space-between", alignItems: "center"}}>
                    <h3 style={{display: "flex", alignSelf: "flex-start"}}><FontAwesomeIcon icon={faUtensils}/> List
                        of Hawkers</h3>
                    <InputGroup style={{width: "25%"}} size="sm" className="search-bar">
                        <FormControl
                            placeholder="Search"
                            name="search"
                            value={search}
                            className={"info-border"}
                            onChange={this.searchChange}
                            onKeyPress={(event) => {
                                if (event.key === "Enter") {
                                    this.searchData(currentPage);
                                }
                            }}
                        />
                        <Button
                            size="sm"
                            variant="outline-info"
                            type="button"
                            onClick={() => this.searchData(currentPage)}
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
                <div style={{"float": "left", padding: "60px 0"}}>
                    Showing Page {currentPage} of {totalPages}
                </div>
                <div style={{"float": "right", padding: "30px 0"}}>
                    <InputGroup className="page-controller">
                        <Button type="button" variant="outline-info" disabled={currentPage === 1}
                                onClick={this.firstPage}>
                            <span style={{paddingRight: "5px"}}>
                                <FontAwesomeIcon icon={faFastBackward}/>
                          </span>
                            First
                        </Button>
                        <Button type="button" variant="outline-info" disabled={currentPage === 1}
                                onClick={this.prevPage}>
                            <span style={{paddingRight: "5px"}}>
                                <FontAwesomeIcon icon={faStepBackward}/>
                            </span>
                            Prev
                        </Button>
                        <FormControl className="page-num" name="currentPage" value={currentPage}
                                     onChange={this.changePage}/>
                        <Button type="button" variant="outline-info"
                                disabled={currentPage === totalPages}
                                onClick={this.nextPage}>
                            <span style={{paddingRight: "5px"}}>
                                <FontAwesomeIcon icon={faStepForward}/>
                            </span>
                            Next
                        </Button>
                        <Button type="button" variant="outline-info"
                                disabled={currentPage === totalPages}
                                onClick={this.lastPage}>
                            <span style={{paddingRight: "5px"}}>
                                <FontAwesomeIcon icon={faFastForward}/>
                            </span>
                            Last
                        </Button>

                    </InputGroup>
                </div>
            </Container>
        )
            ;
    }
}
