package com.sc2006.hawker.controller;

import com.sc2006.hawker.model.FavouriteLink;
import com.sc2006.hawker.service.FavouriteLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/fav")
public class FavouriteLinkController {
    @Autowired
    private FavouriteLinkService favouritelinkservice;

    @GetMapping("/all")
    public List<FavouriteLink> getAllFavouriteLinks() {
        return favouritelinkservice.getAllFavouriteLinks();
    }

    @GetMapping("/user/{username}/count")
    public int getNumberOfFavouriteForUser(@PathVariable String username) {
        return favouritelinkservice.getNumberOfFavouriteForUser(username);
    }

    @GetMapping("/user/{username}/list")
    public List<FavouriteLink> getListOfFavouriteForUser(@PathVariable String username) {
        return favouritelinkservice.getListOfFavouriteForUser(username);
    }

    @PostMapping("/add/favouriteLink")
    public FavouriteLink addFavouriteLink(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String hawkerSerialno = body.get("hawkerSerialno");
        String foodStallSerialno = body.get("foodStallSerialno");
        return favouritelinkservice.addFavouriteLinkToHawker(username, hawkerSerialno, foodStallSerialno);
    }

    /*
        @PostMapping("/add/hawker")
        public FavouriteLink addFavouriteLinkToHawker(@RequestBody Map<String, String> body) {
            String username = body.get("username");
            String serialno = body.get("serialno");
            return favouritelinkservice.addFavouriteLinkToHawker(username, serialno);
        }
        @PostMapping("/add/foodstall")
        public FavouriteLink addFavouriteLinkToFoodStall(@RequestBody Map<String, String> body) {
            String username = body.get("username");
            String serialno = body.get("serialno");
            return favouritelinkservice.addFavouriteLinkToFoodStall(username, serialno);
        }
    */
    @DeleteMapping("/delete/hawker")
    public FavouriteLink deleteFavouriteLinkToHawker(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String serialno = body.get("serialno");
        return favouritelinkservice.deleteFavouriteLinkToHawker(username, serialno);
    }

    @DeleteMapping("/delete/foodstall")
    public FavouriteLink deleteFavouriteLinkToFoodStall(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String serialno = body.get("serialno");
        return favouritelinkservice.deleteFavouriteLinkToFoodStall(username, serialno);
    }

    @GetMapping("/user/{username}/hawker/{serialno}/check")
    public boolean checkFavouriteLinkToHawker(@PathVariable String username, @PathVariable String serialno) {
        return favouritelinkservice.checkFavouriteLinkToHawker(username, serialno);
    }

    @GetMapping("/user/{username}/foodstall/{serialno}/check")
    public boolean checkFavouriteLinkToFoodStall(@PathVariable String username, @PathVariable String serialno) {
        return favouritelinkservice.checkFavouriteLinkToFoodStall(username, serialno);
    }

    @GetMapping("/user/{username}/hawker/count")
    public int getNumberOfFavouriteForHawkerInSingleUser(@PathVariable String username) {
        return favouritelinkservice.getNumberOfFavouriteForHawkerInSingleUser(username);
    }

    @GetMapping("/user/{username}/foodstall/count")
    public int getNumberOfFavouriteForFoodStall(@PathVariable String username) {
        return favouritelinkservice.getNumberOfFavouriteForFoodStallInSingleUser(username);
    }

    @GetMapping("/user/{username}/hawker/list")
    public List<FavouriteLink> getListOfFavouriteForHawkerInSingleUser(@PathVariable String username) {
        return favouritelinkservice.getListOfFavouriteForHawkerInSingleUser(username);
    }

    @GetMapping("/user/{username}/foodstall/list")
    public List<FavouriteLink> getListOfFavouriteForFoodStallInSingleUser(@PathVariable String username) {
        return favouritelinkservice.getListOfFavouriteForFoodStallInSingleUser(username);
    }

    @GetMapping("hawker/{serialno}/count")
    public int getNumberOfFavouriteForHawkerByHawkerSerialNo(@PathVariable String serialno) {
        return favouritelinkservice.getNumberOfFavouriteForHawker(serialno);
    }

    @GetMapping("foodstall/{serialno}/count")
    public int getNumberOfFavouriteForFoodStallByFoodStoreSerialNo(@PathVariable String serialno) {
        return favouritelinkservice.getNumberOfFavouriteForFoodStall(serialno);
    }

    @GetMapping("hawker/{serialno}/list")
    public List<FavouriteLink> getListOfFavouriteForHawker(@PathVariable String serialno) {
        return favouritelinkservice.getListOfFavouriteForHawker(serialno);
    }

    @GetMapping("foodstall/{serialno}/list")
    public List<FavouriteLink> getListOfFavouriteForFoodStall(@PathVariable String serialno) {
        return favouritelinkservice.getListOfFavouriteForFoodStall(serialno);
    }
}
