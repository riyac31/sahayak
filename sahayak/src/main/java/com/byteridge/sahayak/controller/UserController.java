package com.byteridge.sahayak.controller;

import com.byteridge.sahayak.Service.UserService;
import com.byteridge.sahayak.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

//    my two lob api
    @GetMapping("/getAll")
    public List<User> getAll(){
        try{
            return userService.findAll();
        }
        catch (Exception e)
        {
            return Collections.EMPTY_LIST;

        }

    }
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);


    }


}
