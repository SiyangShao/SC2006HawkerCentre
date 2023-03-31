import React, {Component} from 'react';
import {useJsApiLoader, GoogleMap} from "@react-google-maps/api";
import * as Process from "process";
import {GOOGLE_MAPS_API_KEY} from "./GoogleMapKey";


const containerStyle = {
    width: '400px',
    height: '400px'
}

const center = {
    lat: 1.3521,
    lng: 103.8198
}

export default function Map() {
    let isLoaded;
    ({isLoaded} = useJsApiLoader({
        googleMapsApiKey: GOOGLE_MAPS_API_KEY
    }));
    console.log(GOOGLE_MAPS_API_KEY)
    if (!isLoaded) {
        return (
            <div>Loading...</div>
        );
    }
    return (
        <div style={{height: '100vh', width: '100%'}}>
            <GoogleMap center={center} zoom={15} mapContainerStyle={{
                width: '100%',
                height: '70%'
            }}>
            </GoogleMap>
        </div>
    );
}
