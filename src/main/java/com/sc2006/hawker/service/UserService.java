package com.sc2006.hawker.service;

import com.sc2006.hawker.model.User;
import com.sc2006.hawker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Component
public class UserService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\S+@\\S+\\.com$");

    public boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> allUsers() {
        List<User> userDetails = userRepository.findAll();
        return userDetails;
    }

    public void addNewUser(User user) {
        Optional<User> userByUsername = userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (userByUsername.isPresent()) {
            throw new IllegalStateException("username found");
        }
        boolean validEmail = isValidEmail(user.getEmail());
        if (validEmail) {
            userRepository.save(user);
            System.out.println("Valid email.");
        } else
            System.out.println("Invalid email.");
    }

    @Transactional
    public void updateUser(User user) {
        Optional<User> checkUser = userRepository.findUserByUsername(user.getUsername());
        if (checkUser.isEmpty()) {
            throw new IllegalStateException("user does not exist.");
        }

        Optional<User> currentUser = userRepository.findUserByUsername(user.getUsername());
        System.out.println(currentUser);

        if (user.getPassword() != null && user.getPassword().length() > 0)
            mongoTemplate.update(User.class)
                    .matching(Criteria.where("username").is(user.getUsername()))
                    .apply(new Update().set("password", user.getPassword()))
                    .first();
        else
            throw new IllegalStateException("new password requirements does not match");
    }

    public boolean loginUser(User user) {
        Optional<User> checkUser = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (checkUser.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean deleteUser(User user) {
        Optional<User> checkUser = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (checkUser.isEmpty()) {
            return false;
        }
        userRepository.delete(checkUser.get());
        return true;
    }
}
