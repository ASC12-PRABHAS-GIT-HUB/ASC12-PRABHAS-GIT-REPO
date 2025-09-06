package com.auth.util;


public class PasswordValidator {

    public static boolean isValid(String password) {
        return password != null && password.length() >= 6; // simple rule
    }
}
