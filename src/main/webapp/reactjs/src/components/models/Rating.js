import axios from "axios";
import React, { useState, useEffect } from "react";

export default function Rating({ hawker }) {
    const [ratingData, setRatingData] = useState(-1);
    useEffect(() => {
        const hawkerID = hawker.serialno;
        axios.get(`http://localhost:8080/api/v1/reviews/hawker/${hawkerID}/rating`)
            .then(response => {
                setRatingData(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, [hawker]);
    if (!isNaN(ratingData)) {
        return (
            <div>
                AverageRating: {ratingData}
            </div>
        );
    }
    return (
        <div>
            AverageRating: No Rating yet
        </div>
    );
}