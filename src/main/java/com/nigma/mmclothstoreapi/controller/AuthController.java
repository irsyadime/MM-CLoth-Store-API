package com.nigma.mmclothstoreapi.controller;

import com.nigma.mmclothstoreapi.constant.Route;
import com.nigma.mmclothstoreapi.model.dto.request.AuthRequest;
import com.nigma.mmclothstoreapi.model.dto.response.CommmonResponse;
import com.nigma.mmclothstoreapi.model.dto.response.LoginResponse;
import com.nigma.mmclothstoreapi.model.dto.response.RegisterResponse;
import com.nigma.mmclothstoreapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(Route.AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody AuthRequest request){
        RegisterResponse response = authService.registerCustomer(request);
        CommmonResponse<RegisterResponse> commmonResponse = CommmonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success register customer")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
    @PostMapping("/register/merchant")
    public ResponseEntity<?> registerMerchant(@RequestBody AuthRequest request){
        RegisterResponse response = authService.registerMerchant(request);
        CommmonResponse<RegisterResponse> commmonResponse = CommmonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success register customer")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        LoginResponse response = authService.login(request);
        CommmonResponse<LoginResponse> commmonResponse = CommmonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Login success")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
}
