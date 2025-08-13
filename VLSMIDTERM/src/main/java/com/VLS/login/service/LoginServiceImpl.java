package com.VLS.login.service;
import com.VLS.login.repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    private final LoginRepo loginRepo;
    @Autowired
    public LoginServiceImpl(LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }


    @Override
    public boolean validLogin(Long id, String password) {
        return loginRepo.findById(id)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
