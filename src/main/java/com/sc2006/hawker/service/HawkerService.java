package com.sc2006.hawker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.sc2006.hawker.model.Hawker;
import com.sc2006.hawker.repository.HawkerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

//import org.json.JSONArray;
//import org.json.JSONObject;

@CrossOrigin(origins="http://localhost:3000")
@Service
public class HawkerService {

    @Autowired
    private final HawkerRepository hawkerrepository;

    public HawkerService(HawkerRepository hawkerrepository) {
        this.hawkerrepository = hawkerrepository;
    }

    //Search for all hawkers
    public List<Hawker> allHawkers(){
        return hawkerrepository.findAll();
    }
    public Page<Hawker> getAllHawkers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return hawkerrepository.findAll(pageable);
    }


    public Page<Hawker> getAllHawkers(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return hawkerrepository.findAll(name, pageable);
    }
    //Search for a single hawker based on serial number
    public Optional<Hawker> singleHawker(String serialno){
        return hawkerrepository.findHawkerBySerialno(serialno);
    }

    //Search Hawker by names
    public Page<Hawker> hawkerByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return hawkerrepository.findHawkerByNameRegex(name, pageable); }

    //Search for Hawkers within 2.5km radius
    public List<Hawker> hawkerByPostalCode(String postalCode) throws JsonProcessingException {
        double[] centerlatlon = getLatLongFromPostalCode(postalCode);
        double centerlat = centerlatlon[0];
        double centerlon = centerlatlon[1];

        List<Hawker> hawkers = hawkerrepository.findAll();
        List<Hawker> nearestHawkers = new ArrayList<>();

//        Map<Hawker, Double> distanceMap = new HashMap<>();

        for (Hawker hawker: hawkers){
            double lat = Double.parseDouble(hawker.getLatitude_hc());
            double lon = Double.parseDouble(hawker.getLongitude_hc());
            double distance = getDistance(centerlat,centerlon, lat, lon);
            if (distance<=2.5){ //Change this to edit the radius of search
                nearestHawkers.add(hawker);
            }
//            distanceMap.put(hawker, distance);
        }

//        List<Hawker> nearestHawkers = distanceMap.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());

        return nearestHawkers;

    };

    //Obtain latitude and longitude from postal code
    public double[] getLatLongFromPostalCode(String postalCode) throws JsonProcessingException {
        String url = "https://nominatim.openstreetmap.org/search?q=" + postalCode + "&format=json&addressdetails=1";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        double[] latlon = new double[2];
        latlon[0] = root.get(0).get("lat").asDouble();
        latlon[1] = root.get(0).get("lon").asDouble();
//        double lat = root.get(0).get("lat").asDouble();
//        double lon = root.get(0).get("lon").asDouble();
//        latlon[0] = lat;
//        latlon[1] = lon;
        return latlon;
    }


    // Helper method to calculate distance using Haversine formula
    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2 - lat1);
        double dLon = deg2rad(lon2 - lon1);
        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // Distance in km
        return d;
    }

    //Converts degree to radians for Haversine formula
    private double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }

//    public

}
