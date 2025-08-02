package com.blibli.Customer.services;

import com.blibli.Customer.dto.AuthRequest;
import com.blibli.Customer.dto.CustomerRegistrationDTO;
import com.blibli.Customer.entity.Customer;

public interface AuthServices {
    Customer register(CustomerRegistrationDTO customerRegistrationDTO);

    Customer login(AuthRequest authRequest);
}
