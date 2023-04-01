import React from "react";
import axios from "axios";

export default function Rating(hawker) {
    const hawkerID = hawker.serialno;
    const rating = axios.get(`http://localhost:8080/api/hawkers/${hawkerID}/rating`);
}