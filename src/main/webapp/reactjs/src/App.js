import React from 'react';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";

import NavigationBar from "./components/components/NavigationBar";
import Welcome from "./components/welcome/Welcome"
import Footer from "./components/components/Footer"
import HawkerList from "./components/hawkerlist/HawkerList"
import Nearest from "./components/map/Nearest";
import Feedback from "./components/feedback/Feedback"
import EditHawkers from "./components/edithawker/EditHawkers";
import Login from "./components/account/Login";
import Register from "./components/account/Register";
import Update from "./components/account/Update";
import EditHawkerCentreReview from "./components/reviews/EditHawkerCentreReview";
import EditFoodStallReview from "./components/reviews/EditFoodStallReview";
import ViewFoodStallReview from "./components/reviews/ViewFoodStallReview";

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
                        <Route path="/" element={<Welcome/>}/>
                        {/*<Route path="/add" component={Hawker}/>*/}
                        <Route path="/welcome" element={<Welcome/>}/>
                        <Route path="/list" element={<HawkerList/>}/>
                        <Route path="/nearest" element={<Nearest/>}/>
                        {/*TODO: Change edit into Favourite Link*/}
                        <Route path="/edit" element={<EditHawkers/>}/>
                        <Route path="/feedback" element={<Feedback/>}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="/register" element={<Register/>}/>
                        <Route path="/update" element={<Update/>}/>
                        <Route path="/editReview/hawker" element={<EditHawkerCentreReview/>}/>
                        <Route path="/editReview/foodStall" element={<EditFoodStallReview/>}/>
                        <Route path="/viewReview/foodStall" element={<ViewFoodStallReview/>}/>
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
