package com.european.services;

import com.european.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails getUser(String id);
    UserDetails saveUser(User user);
    String generateId();
}
