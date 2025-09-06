
package com.demo.userService;

import com.demo.userEntity.UserIdEntity;
import com.demo.userRepo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImplement implements UserServiceInterface{
    private final UserRepo userRepo;
    @Autowired
    public UserServiceImplement(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserIdEntity getById(String userId) {
        return userRepo.findByUserId(userId);
    }

    @Override
    public List<UserIdEntity> getByName(String userName) {
        return userRepo.findByUserNameIgnoreCase(userName);
    }
}
