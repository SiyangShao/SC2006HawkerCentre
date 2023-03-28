import React from 'react';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";

import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome"
import Footer from "./components/Footer"
import HawkerList from "./components/HawkerList"
import Feedback from "./components/Feedback"
import EditHawkers from "./components/Edit Hawkers";

function App() {
    const marginTop={
        marginTop:"20px"
    };

  return (
      <Router>
        <div className="App">
            <NavigationBar/>
        <Container>
            <Row>
                <Col lg={12} style={marginTop}>
                    <Routes>
                        {/*<Route path="/add" component={Hawker}/>*/}
                        <Route path="/welcome" element={<Welcome/>}/>
                        <Route path="/list" element={<HawkerList/>}/>
                        <Route path="/edit" element={<EditHawkers/>}/>
                        <Route path="/feedback" element={<Feedback/>}/>
                    </Routes>
                </Col>
            </Row>
        </Container>
        <Footer/>
        </div>
      </Router>
  );
}

export default App;
