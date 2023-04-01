import React from 'react';

import {Nav, Navbar, Container, Button} from 'react-bootstrap';
import {Link} from 'react-router-dom';

import './NavigationBar.css';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSignInAlt, faUserPlus, faSignOutAlt, faPenToSquare, faUser} from "@fortawesome/free-solid-svg-icons";
import {confirmLogout} from "./Logout";

function NavigationBarRight() {
    // get current user name
    const username = sessionStorage.getItem("username");
    if (sessionStorage.getItem("isLoggedIn") === "true") {
        return (
            <Nav className="navbar-right">
                <Link to={window.location.pathname} className="navbar-brand"><FontAwesomeIcon
                    icon={faUser}/> Welcome {username}</Link>
                <Link to="#" onClick={confirmLogout} className="navbar-brand"><FontAwesomeIcon icon={faSignOutAlt}/> Logout</Link>
                <Link to={"update"} className="navbar-brand"><FontAwesomeIcon icon={faPenToSquare}/> Update</Link>
            </Nav>
        );
    } else {
        return (
            <Nav className="navbar-right">
                <Link to={"register"} className="navbar-brand"><FontAwesomeIcon icon={faUserPlus}/> Register</Link>
                <Link to={"login"} className="navbar-brand"><FontAwesomeIcon icon={faSignInAlt}/> Login</Link>
            </Nav>
        );
    }
}

class NavigationBar extends React.Component {

    render() {
        return (
            <Navbar bg="dark" variant="dark" className="navigation-bar">
                <Container>
                    <span className="my-logo">
                        <Link to={"welcome"} className="navbar-brand">
                            <img src="https://cdn-icons-png.flaticon.com/512/1045/1045703.png" width="25" height="25"
                                 alt="brand"/>Hawkewhen</Link>
                    </span>
                    <Nav className="me-auto">
                        <Link to={"list"} className="navbar-brand">Hawker List</Link>
                        <Link to={"nearest"} className="navbar-brand">Hawkers Near You</Link>
                        <Link to={"edit"} className="navbar-brand">Favourite Link</Link>
                        <Link to={"feedback"} className="navbar-brand">Feedback</Link>

                    </Nav>
                    <NavigationBarRight/>
                </Container>
            </Navbar>
        );
    }
}

export default NavigationBar;
