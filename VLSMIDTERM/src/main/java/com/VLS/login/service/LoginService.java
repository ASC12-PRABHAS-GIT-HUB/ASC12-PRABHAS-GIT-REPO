package com.VLS.login.service;

import com.VLS.login.entity.LoginEntity;

public interface LoginService {
    boolean validLogin(Long id,String password);
}
