package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.model.entity.AppUser;
import com.nigma.mmclothstoreapi.model.entity.UserCredential;
import com.nigma.mmclothstoreapi.repository.UserCredentialRepository;
import com.nigma.mmclothstoreapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository userCredentialRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        UserCredential userCredential = userCredentialRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Invalid credential"));

        return getAppUser(userCredential);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("Invalid credential")
        );
        return getAppUser(userCredential);
    }

    @Override
    public UserCredential getByContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userCredentialRepository.findByUsername(authentication.getPrincipal().toString())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

    private static AppUser getAppUser(UserCredential userCredential) {
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }
}
