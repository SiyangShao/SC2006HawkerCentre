import React from "react";
import {Button} from "react-bootstrap";

export default function ViewFoodStallReviewButton({foodStallID, foodStallName, hawkerID, hawkerName}) {
    return (
        <Button onClick={() => {
            sessionStorage.setItem("hawkerID", hawkerID);
            sessionStorage.setItem("hawkerName", hawkerName);
            sessionStorage.setItem("foodStallID", foodStallID);
            sessionStorage.setItem("foodStallName", foodStallName);
            // alert("hawkerID: " + sessionStorage.getItem("hawkerID"));
            window.location.href = "/viewReview/foodStall";
        }
        }
                variant="primary"
                size="sm"
        >
            View Review
        </Button>
    );
}