import React from "react";
import {Button} from "react-bootstrap";

export default function EditReviewButtonForFoodStall({hawkerID, hawkerName, foodStallID, foodStallName}) {
    return (
        <Button onClick={() => {
            const loginStatus = sessionStorage.getItem("isLoggedIn");
            if (loginStatus !== "true") {
                alert("Please login first before publishing a review!");
                window.location.href = "/login";
            } else {
                let foodstoreSerail = hawkerID + hawkerName + foodStallID + foodStallName;
                sessionStorage.setItem("hawkerID", hawkerID);
                sessionStorage.setItem("hawkerName", hawkerName);
                sessionStorage.setItem("foodStallID", foodstoreSerail);
                sessionStorage.setItem("foodStallName", foodStallName);
                // alert("hawkerID: " + sessionStorage.getItem("hawkerID"));
                window.location.href = "/editReview/foodStall";
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
