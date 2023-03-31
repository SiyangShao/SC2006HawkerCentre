import React from 'react';

import {Nav, Navbar, Container} from 'react-bootstrap';
import {Link} from 'react-router-dom';

import './NavigationBar.css';

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
                        <Link to={"edit"} className="navbar-brand">Edit Hawkers</Link>
                        <Link to={"feedback"} className="navbar-brand">Feedback</Link>

                    </Nav>
                </Container>
            </Navbar>
        );
    }
}

export default NavigationBar;