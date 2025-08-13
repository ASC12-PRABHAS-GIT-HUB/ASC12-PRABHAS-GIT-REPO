package com.VLS.login.repo;

import com.VLS.login.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<LoginEntity,Long> {
}
