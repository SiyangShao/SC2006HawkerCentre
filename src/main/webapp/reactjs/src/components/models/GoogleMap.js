import React, { useState, useEffect } from 'react';
import { useJsApiLoader } from '@react-google-maps/api';

function GoogleMap(props) {
    const { markersData } = props;
    const [map, setMap] = useState(null);
    const [markers, setMarkers] = useState([]);

    const { isLoaded } = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: 'AIzaSyDyp9DGeg5DXBpSTrAcPdFaSWx0YUZk5Nc'
    });

    useEffect(() => {
        if (isLoaded) {
            const map = new window.google.maps.Map(document.getElementById('map'), {
                center: { lat: markersData[0].lat, lng: markersData[0].lng },
                zoom: 14
            });

            const markers = markersData.map((data) => {
                const marker = new window.google.maps.Marker({
                    position: { lat: data.lat, lng: data.lng },
                    map: map,
                    title: data.title
                });
                return marker;
            });

            setMap(map);
            setMarkers(markers);
        }
    }, [isLoaded]);

    return (
        <div id="map" style={{ height: '400px' }}></div>
    );
}

export default GoogleMap;
