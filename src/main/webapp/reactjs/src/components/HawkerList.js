import React, {Component} from 'react';

import "./HawkerList.css"

import {Card, Col, Row, Container, InputGroup, FormControl, Button} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSearch, faTimes, faUtensils} from '@fortawesome/free-solid-svg-icons';
import {faStepBackward, faStepForward, faFastForward, faFastBackward} from "@fortawesome/free-solid-svg-icons";

import axios from'axios';


export default class HawkerList extends Component {

    constructor(props) {
        super(props);
        this.state= {
            hawkers: [],
            search: '',
            currentPage: 1,
            hawkersPerPage: 9
            };
        }

    componentDidMount(){
        this.findAllHawkers(this.state.currentPage);
    }
    findAllHawkers(currentPage) {
        currentPage -= 1
        axios.get("http://localhost:8080/api/v1/hawkersp?page="+currentPage+"&size="+this.state.hawkersPerPage)
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

    searchData = (currentPage) => {
        currentPage = 0
        axios.get("http://localhost:8080/api/v1/hawkers/search?name="+this.state.search+"&page="+currentPage+"&size="+this.state.hawkersPerPage)
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


    changePage = event => {
        let targetPage = parseInt(event.target.value);
        this.findAllHawkers(targetPage);
        this.setState({
            [event.target.name]: targetPage
        });
    };

    firstPage = () => {
        let firstPage = 1;
        if(this.state.currentPage > 1) {
            this.findAllHawkers(firstPage);
        }
    };
    prevPage = () => {
        let prevPage = 1;
        if(this.state.currentPage > 1) {
            this.findAllHawkers(this.state.currentPage - prevPage);
        }
    };
    nextPage = () => {
        if(this.state.currentPage < Math.ceil(this.state.totalElements / this.state.hawkersPerPage)) {
            this.findAllHawkers(this.state.currentPage + 1)
        }
    };
    lastPage = () => {
        let condition = Math.ceil(this.state.totalElements / this.state.hawkersPerPage);
        if(this.state.currentPage < condition) {
            this.findAllHawkers(condition);
        }
    };

    searchChange = event =>{
        this.setState({
            [event.target.name]: event.target.value
        })
    };

    cancelSearch = () => {
        this.setState({"search":''});
        this.findAllHawkers(1);
    };

    render() {
        const {hawkers, currentPage, hawkersPerPage, search} = this.state;
        const totalPages = Math.ceil(this.state.totalElements / hawkersPerPage);

        return (
            <Container className="my-container">
                <div style={{display: "flex", justifyContent: "space-between", alignItems:"center"}}>
                    <h3 style={{display:"flex", alignSelf:"flex-start"}}><FontAwesomeIcon icon={faUtensils} /> List of Hawkers</h3>
                    <InputGroup style={{width:"25%"}} size="sm">
                        <FormControl placeholder="Search" name="search" value={search}
                                     className={"info-border"}
                                     onChange={this.searchChange}/>
                        <Button size="sm" variant="outline-info" type="button" onClick={() => this.searchData(currentPage)}>
                            <FontAwesomeIcon icon={faSearch}/>
                        </Button>
                        <Button size="sm" variant="outline-danger" type="button" onClick={() => this.cancelSearch(1)}>
                            <FontAwesomeIcon icon={faTimes}/>
                        </Button>
                    </InputGroup>
                </div>
                <Row xs={1} md={3} className="g-4">
                    {hawkers.map((hawker) => (
                        <Col key={hawker._id}>
                            <Card>
                                <Card.Img variant="top" src={hawker.photourl} />
                                <Card.Body>
                                    <Card.Title>{hawker.name}</Card.Title>
                                    <Card.Text>{hawker.description_myenv}</Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
                <div style={{"float":"left", padding:"10px 0"}}>
                    Showing Page {currentPage} of {totalPages}
                </div>
                <div style={{"float":"right", padding:"10px 0"}}>
                    <InputGroup>
                        <Button type="button" variant="outline-info" disabled={currentPage===1 ? true : false}
                            onClick={this.firstPage}>
                            <span style={{paddingRight: "5px"}}>
                                <FontAwesomeIcon icon={faFastBackward} />
                          </span>
                            First
                        </Button>
                        <Button type="button" variant="outline-info" disabled={currentPage===1 ? true : false}
                            onClick={this.prevPage}>
                            <span style={{paddingRight: "5px"}}>
                                <FontAwesomeIcon icon={faStepBackward} />
                            </span>
                            Prev
                        </Button>
                        <FormControl className={"page-num"} name="currentPage" value={currentPage}
                            onChange={this.changePage}/>
                        <Button type="button" variant="outline-info" disabled={currentPage===totalPages ? true : false}
                            onClick={this.nextPage}>
                            <span style={{paddingRight: "5px"}}>
                                <FontAwesomeIcon icon={faStepForward} />
                            </span>
                            Next
                        </Button>
                        <Button type="button" variant="outline-info" disabled={currentPage===totalPages ? true : false}
                            onClick={this.lastPage}>
                            <span style={{paddingRight: "5px"}}>
                                <FontAwesomeIcon icon={faFastForward} />
                            </span>
                        Last
                        </Button>

                    </InputGroup>
                </div>
            </Container>
        );
    }
}
