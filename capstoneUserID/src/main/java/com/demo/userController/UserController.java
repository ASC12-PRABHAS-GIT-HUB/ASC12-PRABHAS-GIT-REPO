package com.demo.userController;

import com.demo.userEntity.UserIdEntity;
import com.demo.userService.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {
    private final UserServiceInterface userServiceInterface;

    @Autowired
    public UserController(UserServiceInterface userServiceInterface){
        this.userServiceInterface=userServiceInterface;
    }
    @GetMapping
    UserIdEntity getbyId(String userId){
        return userServiceInterface
    }
}
