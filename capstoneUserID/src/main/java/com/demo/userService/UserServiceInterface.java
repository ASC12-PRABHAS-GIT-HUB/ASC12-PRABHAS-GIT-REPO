
package com.demo.userService;

import com.demo.userEntity.UserIdEntity;

import java.util.List;

public interface UserServiceInterface {
    public UserIdEntity getById(String userId);
    public List<UserIdEntity> getByName(String userName);

}
