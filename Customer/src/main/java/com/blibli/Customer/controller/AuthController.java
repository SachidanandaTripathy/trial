package com.blibli.Customer.controller;

import com.blibli.Customer.dto.AuthRequest;
import com.blibli.Customer.dto.CustomerRegistrationDTO;
import com.blibli.Customer.entity.Customer;
import com.blibli.Customer.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class AuthController {

    @Autowired
    private AuthServices authServices;

    @PostMapping("/register")
    public ResponseEntity<Customer> customerRegistration(@RequestBody CustomerRegistrationDTO customerRegistrationDTO){
        Customer customer=authServices.register(customerRegistrationDTO);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> customerLogin(@RequestBody AuthRequest authRequest){
        Customer customer=authServices.login(authRequest);
        if(customer!=null){
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("unsuccessful",HttpStatus.OK);
        }
    }
}
