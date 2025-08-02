package com.blibli.Customer.services;

import com.blibli.Customer.dto.AuthRequest;

public interface JwtServices {
    String generateToken(AuthRequest authRequest);
}
