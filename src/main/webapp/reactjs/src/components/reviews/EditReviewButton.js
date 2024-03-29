import React from "react";
import {Button} from "react-bootstrap";

export default function EditReviewButton({hawkerID, hawkerName}) {
    return (
        <Button onClick={() => {
            const loginStatus = sessionStorage.getItem("isLoggedIn");
            if (loginStatus !== "true") {
                alert("Please login first before publishing a review!");
                window.location.href = "/login";
            } else {
                sessionStorage.setItem("hawkerID", hawkerID);
                sessionStorage.setItem("hawkerName", hawkerName);
                // alert("hawkerID: " + sessionStorage.getItem("hawkerID"));
                window.location.href = "/editReview/hawker";
            }
        }
        }
                variant="primary"
                size="sm"
        >
Create / Edit Review
        </Button>
    );
    // const loginStatus = sessionStorage.getItem("isLoggedIn");
    // if (loginStatus !== "true") {
    //     window.location.href = "/login";
    //     alert("Please login first before publishing a review!");
    // }
}