package com.myproject.userbackendshopping.controllers;

import com.myproject.userbackendshopping.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    public User login(){
//        Check if email and password in db , if yes return user else throw error
        return null;
    }
    public User signUp(){
//        Store user as is in the db
        return null;
    }
    public ResponseEntity<Void>logout(){
//        Delete token if exixts , return 200 if exists else give 404 error
        return null;
    }
}

//Logic will be in services