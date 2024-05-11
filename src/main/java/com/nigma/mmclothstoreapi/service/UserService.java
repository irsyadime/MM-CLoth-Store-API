package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.entity.AppUser;
import com.nigma.mmclothstoreapi.model.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
    UserCredential getByContext();
}
