package com.mir.app.springbootstudy.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/hello/create")
    public User helloCreate(@RequestBody User user){
        return user;
    }

}
