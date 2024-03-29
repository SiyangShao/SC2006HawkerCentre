package com.sc2006.hawker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sc2006.hawker.model.FavouriteLink;
import com.sc2006.hawker.model.Hawker;
import com.sc2006.hawker.service.HawkerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1") //This mapping will be handled by this controller
public class HawkerController {
    private final HawkerService hawkerservice;

    public HawkerController(HawkerService hawkerservice) {
        this.hawkerservice = hawkerservice;
    }

    @GetMapping("/hawkers")
    public ResponseEntity<List<Hawker>> getAllHawkers(){
        return new ResponseEntity<List<Hawker>>(hawkerservice.allHawkers(), HttpStatus.OK);
    }

    @GetMapping("/hawkersp")
    public ResponseEntity<Page<Hawker>> getAllHawkersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Hawker> hawkersPage = hawkerservice.getAllHawkers(page, size);
        return ResponseEntity.ok(hawkersPage);
    }


    @GetMapping("/hawkers/{serialno}")
    public ResponseEntity<Optional<Hawker>> getSingleHawker(@PathVariable String serialno){
        return new ResponseEntity<Optional<Hawker>>(hawkerservice.singleHawker(serialno), HttpStatus.OK);
    }

    @GetMapping("/hawkers/search")
    public ResponseEntity<Page<Hawker>> getHawkerByName(@RequestParam(defaultValue = "") String name,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Page<Hawker> hawkersPage = hawkerservice.getAllHawkers(name, page, size);
        return ResponseEntity.ok(hawkersPage);
    }

    @GetMapping("/hawkers/search/nearest")
    public ResponseEntity<List<Hawker>> getNearestHawkersByPostalCode(@RequestParam String postalcode) throws JsonProcessingException {
        return new ResponseEntity<List<Hawker>>(hawkerservice.hawkerByPostalCode(postalcode), HttpStatus.OK);
    }

    @PutMapping("/hawkers/fromfav")
    public ResponseEntity<List<Hawker>> getHawkersFromFav(@RequestBody List<FavouriteLink> favouriteLinks) throws JsonProcessingException {
        return new ResponseEntity<List<Hawker>>(hawkerservice.hawkersFromFav(favouriteLinks), HttpStatus.OK);
    }

//    @GetMapping("/hawkers/search/ophours")
//    public ResponseEntity<List<Map<String, String>>> getHawkerOPHours(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String postalcode,
//            @RequestParam(required = false) String serialno) throws JsonProcessingException {
//        List<Hawker> hawkers;
//        if(name!=null){
//            hawkers = hawkerservice.hawkerByName(name, );
//        } else if (postalcode != null){
//            hawkers = hawkerservice.hawkerByPostalCode(postalcode);
//        } else if (serialno != null) {
//            Optional<Hawker> hawker = hawkerservice.singleHawker(serialno);
//            if(hawker.isPresent()) {
//                hawkers = List.of(hawker.get());
//            } else {
//                hawkers = List.of();
//            }
//        } else{
//            hawkers = hawkerservice.allHawkers();
//        }
//
//        List<Map<String, String>> result = new ArrayList<>();
//        for(Hawker hawker : hawkers) {
//            Map<String, String> hawkerMap = new LinkedHashMap<>();
//            hawkerMap.put("name", hawker.getName());
//            hawkerMap.put("q1_cleaningstartdate", hawker.getQ1_cleaningstartdate());
//            hawkerMap.put("q1_cleaningenddate", hawker.getQ1_cleaningenddate());
//            hawkerMap.put("remarks_q1", hawker.getRemarks_q1());
//            hawkerMap.put("q2_cleaningstartdate", hawker.getQ2_cleaningstartdate());
//            hawkerMap.put("q2_cleaningenddate", hawker.getQ2_cleaningenddate());
//            hawkerMap.put("remarks_q2", hawker.getRemarks_q2());
//            hawkerMap.put("q3_cleaningstartdate", hawker.getQ3_cleaningstartdate());
//            hawkerMap.put("q3_cleaningenddate", hawker.getQ3_cleaningenddate());
//            hawkerMap.put("remarks_q3", hawker.getRemarks_q3());
//            hawkerMap.put("q4_cleaningstartdate", hawker.getQ4_cleaningstartdate());
//            hawkerMap.put("q4_cleaningenddate", hawker.getQ4_cleaningenddate());
//            hawkerMap.put("remarks_q4", hawker.getRemarks_q4());
//            hawkerMap.put("other_works_startdate", hawker.getOther_works_startdate());
//            hawkerMap.put("other_works_enddate", hawker.getOther_works_enddate());
//            hawkerMap.put("remarks_other_works", hawker.getRemarks_other_works());
//            result.add(hawkerMap);
//        }
//        return new ResponseEntity<List<Map<String, String>>>(result, HttpStatus.OK);
//    }

}
