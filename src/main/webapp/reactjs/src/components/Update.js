import React, {Component} from 'react';
import {Form, Button, Card} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUndo, faUser, faSignInAlt} from '@fortawesome/free-solid-svg-icons';
import MyToast from './MyToast';

import axios from 'axios';

export default class Update extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.userChange = this.userChange.bind(this);
        this.submitUser = this.submitUser.bind(this);
    }

    initialState = {
        username:'', password:''
    }

    submitUser=event=>{
        event.preventDefault();

        const user = {username: this.state.username, email: "test@gmail.com", password: this.state.password};

        axios.post("http://localhost:8080/api/v1/user/update", user)
            .then((response) =>{
                if(response.data === "success"){
                    window.location.href = "/welcome";
                }
                else{
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}), 3000);
                }
            })
        this.setState(this.initialState);
    }

    resetForm = () =>{
        this.setState(() => this.initialState);
    }

    userChange(event){
        this.setState({[event.target.name]:event.target.value})
    }
    render() {
        const {username, password} = this.state;

        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <MyToast children = {{show:this.state.show, message:"Username Incorrect"}}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"} style={{width: '30rem'}}>
                    <Card.Header>Update</Card.Header>
                    <Form onReset={this.resetForm} onSubmit={this.submitUser} id="registerId">
                        <Card.Body>
                            <Form.Group className="mb-3" controlId="formUsername">
                                <Form.Label>Username</Form.Label>
                                <Form.Control required autoComplete="off"
                                              type="test" name="username" placeholder="Enter username"
                                              onChange={this.userChange} value={username} />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formPassword">
                                <Form.Label>New Password</Form.Label>
                                <Form.Control required autoComplete="off"
                                              type="test" name="password" placeholder="Password"
                                              onChange={this.userChange} value={password}/>
                            </Form.Group>

                            <Button size="sm" variant="success" type="submit">
                                <FontAwesomeIcon icon={faSignInAlt} /> Update
                            </Button>{' '}
                            <Button size="sm" variant="info" type="reset">
                                <FontAwesomeIcon icon={faUndo} /> Reset
                            </Button>
                        </Card.Body>
                    </Form>
                </Card>
            </div>
        );
    }
}
