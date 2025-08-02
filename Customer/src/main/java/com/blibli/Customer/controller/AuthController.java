package com.blibli.Customer.controller;
import com.blibli.Customer.dto.AuthRequest;
import com.blibli.Customer.dto.CustomerRegistrationDTO;
import com.blibli.Customer.entity.Customer;
import com.blibli.Customer.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> customerLogin(@RequestBody AuthRequest authRequest) {
        String token = authServices.verify(authRequest);
        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/get")
    public ResponseEntity<String> customerLogin() {
        return new ResponseEntity<>("SachidanandaTripathy",HttpStatus.OK);
    }
}
