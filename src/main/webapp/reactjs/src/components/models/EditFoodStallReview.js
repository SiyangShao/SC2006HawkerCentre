// // import React, { useState } from "react";
// //
// // export default function EditHawkerCentreReview() {
// //     const username = sessionStorage.getItem("username");
// //     const hawkerID = sessionStorage.getItem("hawkerID");
// //     const hawkerName = sessionStorage.getItem("hawkerName");
// //     const [review, setReview] = useState("");
// //
// //     const handleSubmit = (event) => {
// //         event.preventDefault();
// //         // Here you can send the review to the server
// //         console.log(`Username: ${username}, Hawker ID: ${hawkerID}, Hawker Name: ${hawkerName}, Review: ${review}`);
// //     };
// //
// //     return (
// //         <div>
// //             <h1>Edit Hawker Centre Review</h1>
// //             {/*<h2>Username: {username}</h2>*/}
// //             {/*<h2>Hawker ID: {hawkerID}</h2>*/}
// //             <h3>Hawker Name: {hawkerName}</h3>
// //             <form onSubmit={handleSubmit}>
// //                 <label>
// //                     Review:
// //                     <p>
// //                     <textarea value={review} onChange={(e) => setReview(e.target.value)}/>
// //                     </p>
// //                 </label>
// //                 <p>
// //                 <button type="submit">Submit</button>
// //                 </p>
// //             </form>
// //         </div>
// //     );
// //
// // }
//
// import React, { useState } from "react";
//
// export default function EditHawkerCentreReview() {
//     const username = sessionStorage.getItem("username");
//     const hawkerID = sessionStorage.getItem("hawkerID");
//     const hawkerName = sessionStorage.getItem("hawkerName");
//     const [review, setReview] = useState("");
//     const [rating, setRating] = useState("");
//
//     const handleSubmit = (event) => {
//         event.preventDefault();
//         // Here you can send the review and rating to the server
//         console.log(`Username: ${username}, Hawker ID: ${hawkerID}, Hawker Name: ${hawkerName}, Review: ${review}, Rating: ${rating}`);
//     };
//
//     return (
//         <div>
//             <h1>Edit Hawker Centre Review</h1>
//             {/*<h2>Username: {username}</h2>*/}
//             {/*<h2>Hawker ID: {hawkerID}</h2>*/}
//             <h3>Hawker Name: {hawkerName}</h3>
//             <form onSubmit={handleSubmit}>
//                 <label>
//                     Rating (out of 5):
//                     <input type="number" value={rating} min="1" max="5" onChange={(e) => setRating(e.target.value)}/>
//                 </label>
//                 <br/>
//                 <label>
//                     Review:
//                     <textarea value={review} onChange={(e) => setReview(e.target.value)}/>
//                 </label>
//                 <br/>
//                 <button type="submit">Submit</button>
//             </form>
//         </div>
//     );
//
// }

import React, { useState } from "react";
import axios from "axios";

export default function EditFoodStallReview() {
    const userName = sessionStorage.getItem("username");
    const hawkerSerialno = sessionStorage.getItem("hawkerID");
    const hawkerName = sessionStorage.getItem("hawkerName");
    const foodStallSerialno = sessionStorage.getItem("foodStallID");
    const foodStallName = sessionStorage.getItem("foodStallName");
    const [realReviewBody, setRealReviewBody] = useState("");
    const [rating, setRating] = useState("");

    const handleSubmit = (event) => {
        console.log("username: " + userName);
        console.log("hawkerID: " + hawkerSerialno);
        console.log("hawkerName: " + hawkerName);
        console.log("foodStallID: " + foodStallSerialno);
        console.log("foodStallName: " + foodStallName);
        console.log("review: " + realReviewBody);
        console.log("rating: " + rating);
        const reviewBody = realReviewBody + "\nFor food stall: " + foodStallName;
        event.preventDefault();
        axios.post("http://localhost:8080/api/v1/reviews/create/foodstall", { userName, hawkerSerialno, foodStallSerialno, hawkerName, reviewBody, rating })
            .then(response => {
                console.log("Review created successfully:", response.data);
                alert("Review created successfully");
                // go back to previous page
                window.history.back();
                // You can navigate to another page or show a success message here
            })
            .catch(error => {
                console.error("Error creating review:", error);
                // You can show an error message to the user here
            });
    };

    return (
        <div>
            <h1>Edit Hawker Centre Review</h1>
            {/*<h2>Username: {username}</h2>*/}
            {/*<h2>Hawker ID: {hawkerID}</h2>*/}
            <h3>Hawker Name: {hawkerName}</h3>
            <h3>Food Stall Name: {foodStallName}</h3>
            <form onSubmit={handleSubmit}>
                <label>
                    Rating (out of 5):
                    <input type="number" value={rating} min="1" max="5" onChange={(e) => setRating(e.target.value)} />
                </label>
                <br />
                <label>
                    Review:
                    <textarea value={realReviewBody} onChange={(e) => setRealReviewBody(e.target.value)} />
                </label>
                <br />
                <button type="submit">Submit</button>
            </form>
        </div>
    );

}
