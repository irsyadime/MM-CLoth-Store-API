package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.dto.request.AuthRequest;
import com.nigma.mmclothstoreapi.model.dto.response.LoginResponse;
import com.nigma.mmclothstoreapi.model.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest request);
    RegisterResponse registerMerchant(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
