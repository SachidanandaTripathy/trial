package com.blibli.Customer.services.impl;
import com.blibli.Customer.dto.AuthRequest;
import com.blibli.Customer.dto.CustomerRegistrationDTO;
import com.blibli.Customer.entity.Customer;
import com.blibli.Customer.repositories.CustomerRepositories;
import com.blibli.Customer.services.AuthServices;
import com.blibli.Customer.services.JwtServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServices {

    @Autowired
    private CustomerRepositories customerRepositories;

    @Autowired
    private JwtServices jwtServices;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer register(CustomerRegistrationDTO customerRegistrationDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRegistrationDTO, customer);
        customer.setCustomerPassword(passwordEncoder.encode(customerRegistrationDTO.getCustomerPassword()));
        return customerRepositories.save(customer);
    }

    @Override
    public String verify(AuthRequest authRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getCustomerEmail(),
                            authRequest.getCustomerPassword()
                    )
            );
            if (authenticate.isAuthenticated()) {
                return jwtServices.generateToken(authRequest);
            } else {
                System.out.println("Authentication failed - not authenticated");
            }

        } catch (BadCredentialsException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
