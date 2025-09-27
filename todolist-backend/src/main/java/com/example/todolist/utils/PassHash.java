package com.example.todolist.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PassHash {

        public static String hashPassword(String password) {
            //Salt password
            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(password, salt);
            return hashedPassword;
    }
}
