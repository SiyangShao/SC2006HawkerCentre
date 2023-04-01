import React from 'react';

import './Welcome.css'
import { Container, Row, Col } from 'react-bootstrap';
import GoogleMap from "./models/GoogleMap";

function Welcome() {
    return (
        <Container className="container-fluid">
            <Row>
                <Col>
                    <div className="my-container py-5">
                        <h1 className="display-4">Welcome to Hawkewhen!</h1>
                        <p className="lead">
                            Don't waste your time going Hawkers that are NOT open!
                        </p>
                    </div>
                </Col>
            </Row>
        </Container>
    );
}

export default Welcome;
