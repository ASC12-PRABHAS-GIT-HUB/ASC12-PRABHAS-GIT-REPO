
package com.demo.userRepo;

import com.demo.userEntity.UserIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<UserIdEntity,Integer> {
    UserIdEntity findByUserId(String userId);
    List<UserIdEntity> findByUserNameIgnoreCase(String userName);

}

