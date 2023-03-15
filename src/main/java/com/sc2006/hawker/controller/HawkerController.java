package com.sc2006.hawker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sc2006.hawker.model.Hawker;
import com.sc2006.hawker.service.HawkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/hawkers") //This mapping will be handled by this controller
public class HawkerController {
    private final HawkerService hawkerservice;

    public HawkerController(HawkerService hawkerservice) {
        this.hawkerservice = hawkerservice;
    }

    @GetMapping
    public ResponseEntity<List<Hawker>> getAllHawkers() {
        return new ResponseEntity<>(hawkerservice.allHawkers(), HttpStatus.OK);
    }

    @GetMapping("/{serialno}")
    public ResponseEntity<Optional<Hawker>> getSingleHawker(@PathVariable String serialno) {
        return new ResponseEntity<>(hawkerservice.singleHawker(serialno), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Hawker>> getHawkerByName(@RequestParam(defaultValue = "") String name) {
        return new ResponseEntity<>(hawkerservice.hawkerByName(name), HttpStatus.OK);
    }

    @GetMapping("/search/nearest")
    public ResponseEntity<List<Hawker>> getNearestHawkersByPostalCode(@RequestParam String postalcode) throws JsonProcessingException {
        return new ResponseEntity<>(hawkerservice.hawkerByPostalCode(postalcode), HttpStatus.OK);
    }

    @GetMapping("/search/ophours")
    public ResponseEntity<List<Map<String, String>>> getHawkerOPHours(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String postalcode,
            @RequestParam(required = false) String serialno) throws JsonProcessingException {
        List<Hawker> hawkers;
        if (name != null) {
            hawkers = hawkerservice.hawkerByName(name);
        } else if (postalcode != null) {
            hawkers = hawkerservice.hawkerByPostalCode(postalcode);
        } else if (serialno != null) {
            Optional<Hawker> hawker = hawkerservice.singleHawker(serialno);
            if (hawker.isPresent()) {
                hawkers = List.of(hawker.get());
            } else {
                hawkers = List.of();
            }
        } else {
            hawkers = hawkerservice.allHawkers();
        }

        List<Map<String, String>> result = new ArrayList<>();
        for (Hawker hawker : hawkers) {
            Map<String, String> hawkerMap = new LinkedHashMap<>();
            hawkerMap.put("name", hawker.getName());
            hawkerMap.put("q1_cleaningstartdate", hawker.getQ1_cleaningstartdate());
            hawkerMap.put("q1_cleaningenddate", hawker.getQ1_cleaningenddate());
            hawkerMap.put("remarks_q1", hawker.getRemarks_q1());
            hawkerMap.put("q2_cleaningstartdate", hawker.getQ2_cleaningstartdate());
            hawkerMap.put("q2_cleaningenddate", hawker.getQ2_cleaningenddate());
            hawkerMap.put("remarks_q2", hawker.getRemarks_q2());
            hawkerMap.put("q3_cleaningstartdate", hawker.getQ3_cleaningstartdate());
            hawkerMap.put("q3_cleaningenddate", hawker.getQ3_cleaningenddate());
            hawkerMap.put("remarks_q3", hawker.getRemarks_q3());
            hawkerMap.put("q4_cleaningstartdate", hawker.getQ4_cleaningstartdate());
            hawkerMap.put("q4_cleaningenddate", hawker.getQ4_cleaningenddate());
            hawkerMap.put("remarks_q4", hawker.getRemarks_q4());
            hawkerMap.put("other_works_startdate", hawker.getOther_works_startdate());
            hawkerMap.put("other_works_enddate", hawker.getOther_works_enddate());
            hawkerMap.put("remarks_other_works", hawker.getRemarks_other_works());
            result.add(hawkerMap);
        }
        return new ResponseEntity<List<Map<String, String>>>(result, HttpStatus.OK);
    }

}
