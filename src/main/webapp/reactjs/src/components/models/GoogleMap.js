import React, { useState, useEffect } from 'react';
import { useJsApiLoader } from '@react-google-maps/api';

function GoogleMap(props) {
    const lat = parseFloat(props.lat);
    const lng = parseFloat(props.lng);
    const [map, setMap] = useState(null);
    const [marker, setMarker] = useState(null);

    const { isLoaded } = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: 'AIzaSyDyp9DGeg5DXBpSTrAcPdFaSWx0YUZk5Nc'
    });

    useEffect(() => {
        if (isLoaded) {
            const map = new window.google.maps.Map(document.getElementById('map'), {
                center: { lat: lat, lng: lng },
                zoom: 15
            });
            const marker = new window.google.maps.Marker({
                position: { lat: lat, lng: lng },
                map: map,
                title: 'My Marker'
            });
            setMap(map);
            setMarker(marker);
        }
    }, [isLoaded]);

    return (
        <div id="map" style={{ height: '400px' }}></div>
    );
}

export default GoogleMap;
