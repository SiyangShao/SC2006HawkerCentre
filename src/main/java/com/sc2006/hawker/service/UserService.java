package com.sc2006.hawker.service;

import com.sc2006.hawker.model.User;
import com.sc2006.hawker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public int addNewUser(User user) {
        Optional<User> userByUsername = userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail());
        Optional<User> email = userRepository.findUserByEmail(user.getEmail());
        Optional<User> username = userRepository.findUserByUsername(user.getUsername());

        boolean validEmail = isValidEmail(user.getEmail());
        if(validEmail){
            if(email.isEmpty()){
                if(username.isEmpty()){
                    userRepository.save(user);
                    System.out.println("Account successfully created.");
                    return 4; //account created
                }else{
                    System.out.println("Username already exist.");
                    return 3; //username already exists
                }
            }else{
                System.out.println("Email already exist.");
                return 2; //email already exists
            }
        }else{
            System.out.println("Invalid email.");
            return 1; //invalid email
        }
    }

    @Transactional
    public boolean updateUser(User user) {
        Optional<User> checkUser = userRepository.findUserByUsername(user.getUsername());
        if (checkUser.isEmpty()) {
            return false;
        }
        if (user.getPassword() != null && user.getPassword().length() > 0) {
            mongoTemplate.update(User.class)
                    .matching(Criteria.where("username").is(user.getUsername()))
                    .apply(new Update().set("password", user.getPassword()))
                    .first();
            System.out.println("Update successful");
            return true;
        } else
            return false;
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
