package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
