import React from 'react';

import './Welcome.css'
import {Navbar, Container, Col} from "react-bootstrap";

class Footer extends React.Component {
    render(){
        let fullYear = new Date().getFullYear();
        return (
            <Navbar fixed="bottom" className="my-footer">
                <Container>
                    <Col lg={12} className="text-center text-muted">
                         <div>{fullYear} - {fullYear+1}, All rights Reserved by Hawkewhen</div>
                    </Col>
                </Container>
            </Navbar>
        )
    }
}

export default Footer