import React from "react";
import axios from "axios";
import {Button} from "react-bootstrap";

let clickToggle = (hawkerSerialno) => {
    const username = sessionStorage.getItem("username");
    const status = sessionStorage.getItem("isLoggedIn");
    if (status !== 'true') {
        alert("Please login to toggle favourite Hawkers!");
        window.location.href = "/login";
    }
    axios.put("http://localhost:8080/api/v1/fav/update", {username, hawkerSerialno}).then(response => {
        console.log("Link changed successfully: ",
            response.data
        );
        alert("Link changed successfully: " + response.data);
    }).catch(error => {
        console.log("Error in ToggleFavouriteStatus: " + error);
        alert("Error in ToggleFavouriteStatus: " + error);
    });
}

export default function ToggleFavouriteStatus({hawkerSerialno}) {


    return (
        <div>
            <Button onClick={() => clickToggle(hawkerSerialno)
            }> Toggle Favourite Status
            </Button>
        </div>
    )
}