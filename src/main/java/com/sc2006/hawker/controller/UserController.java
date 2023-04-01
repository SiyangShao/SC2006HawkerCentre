package com.sc2006.hawker.controller;

import com.sc2006.hawker.service.UserService;
import com.sc2006.hawker.model.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.allUsers(), HttpStatus.OK);
    }

    @PostMapping("/register/trial")
    public ResponseEntity<User> registerNewUserTrail(@RequestBody User user) {
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createNewAccount(@RequestBody User user) {
        boolean exist = userService.addNewUser(user);
        if (exist==true)
            return new ResponseEntity<String>("success", HttpStatus.OK);
        else
            return new ResponseEntity<String> (HttpStatus.NO_CONTENT);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUserAccount(@RequestBody User user) {
        boolean exist = userService.updateUser(user);
        if (exist==true)
            return new ResponseEntity<String>("success", HttpStatus.OK);
        else
            return new ResponseEntity<String> (HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAccount(@RequestBody User user) {
        boolean exist = userService.loginUser(user);
        if (exist==true)
            return new ResponseEntity<String>("success", HttpStatus.OK);
        else
            return new ResponseEntity<String> (HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestBody User user) {
        boolean exist = userService.deleteUser(user);
        if (exist)
            return new ResponseEntity<String>("User account deleted. Goodbye!", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Delete failed", HttpStatus.OK);
    }
}
