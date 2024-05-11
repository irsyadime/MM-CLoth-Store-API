package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.constant.ERole;
import com.nigma.mmclothstoreapi.model.dto.request.AuthRequest;
import com.nigma.mmclothstoreapi.model.dto.response.LoginResponse;
import com.nigma.mmclothstoreapi.model.dto.response.RegisterResponse;
import com.nigma.mmclothstoreapi.model.entity.*;
import com.nigma.mmclothstoreapi.repository.UserCredentialRepository;
import com.nigma.mmclothstoreapi.security.JwtUtil;
import com.nigma.mmclothstoreapi.service.AuthService;
import com.nigma.mmclothstoreapi.service.CustomerService;
import com.nigma.mmclothstoreapi.service.MerchantService;
import com.nigma.mmclothstoreapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerCustomer(AuthRequest request) {
        try{
            Role role = roleService.getOrSave(ERole.CUSTOMER);
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            Customer customer = Customer.builder()
                    .name(request.getName())
                    .phone(request.getPhone())
                    .userCredential(userCredential)
                    .build();
            customerService.create(customer);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(role.getName().toString())
                    .build();
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"user already exist");
        }

    }

    @Override
    public RegisterResponse registerMerchant(AuthRequest request) {
        try{
            Role role = roleService.getOrSave(ERole.MERCHANT);
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            Merchant merchant = Merchant.builder()
                    .name(request.getName())
                    .phone(request.getPhone())
                    .userCredential(userCredential)
                    .build();
            merchantService.create(merchant);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(role.getName().toString())
                    .build();
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"user already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername().toLowerCase(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);
        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
