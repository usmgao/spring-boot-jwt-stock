package com.bezkoder.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.springjwt.HelpUtil;
import com.bezkoder.springjwt.exception.UserNotFoundException;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;

//import com.ming.boot.HelpUtil;
//import com.ming.boot.exception.UserNotFoundException;
//import com.ming.boot.model.User;
//import com.ming.boot.repository.UserRepository;

import java.util.List;

/* Created by Arjun Gautam */
@RestController
//@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
    	List<User> users = userRepository.findAll();
    	if(users == null)
    		return null;
    	
    	HelpUtil.ErrorServerLog("users suze: "+users.size());
    	for(int i = 0; i<users.size(); i++) {
    		HelpUtil.ErrorServerLog("user: "+users.get(i).toSimpleString());
    	}
    	return users;
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }



}
