// import React from 'react';
//
// import {Form, Button} from 'react-bootstrap'
//
// class Feedback extends React.Component {
//
// }
//
// export default Feedback;
import React, {useState} from 'react';
import {Form, Button} from 'react-bootstrap';

function Feedback() {
    const [feedback, setFeedback] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log('Feedback received:', feedback);
    };

    return (
        <div>
            <h1>Feedback Form</h1>
            <Form onSubmit={handleSubmit}>
                <Form.Group>
                    <Form.Label>Please provide your feedback:</Form.Label>
                    <Form.Control type="text" value={feedback} onChange={(e) => setFeedback(e.target.value)}/>
                </Form.Group>
                <Button type="submit" onClick={
                    () => {
                        if (sessionStorage.getItem("isLoggedIn") !== "true") {
                            alert("Please login first before submitting your feedback!");
                            window.location.href = "/login";
                        } else if (feedback === "") {
                            alert("Please provide your feedback!");
                        } else {
                            alert("Thank you for your feedback! We will take your feedback into consideration. Any mistakes about hawkers will be corrected after manual verification.");
                            // goto homepage
                            window.location.href = "/";
                        }
                    }
                }>Submit</Button>
            </Form>
        </div>
    );
}

export default Feedback;