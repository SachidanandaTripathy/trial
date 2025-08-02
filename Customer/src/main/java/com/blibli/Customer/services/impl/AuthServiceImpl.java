package com.blibli.Customer.services.impl;
import com.blibli.Customer.dto.AuthRequest;
import com.blibli.Customer.dto.CustomerRegistrationDTO;
import com.blibli.Customer.entity.Customer;
import com.blibli.Customer.repositories.CustomerRepositories;
import com.blibli.Customer.services.AuthServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AuthServiceImpl implements AuthServices {

    @Autowired
    private CustomerRepositories customerRepositories;

    @Override
    public Customer register(CustomerRegistrationDTO customerRegistrationDTO) {
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerRegistrationDTO,customer);
        return customerRepositories.save(customer);
    }

    @Override
    public Customer login(AuthRequest authRequest) {
        Customer customer = customerRepositories.findByCustomerEmail(authRequest.getCustomerEmail());

        if (customer != null &&
                customer.getCustomerEmail().equals(authRequest.getCustomerEmail()) &&
                customer.getCustomerPassword().equals(authRequest.getCustomerPassword())) {
            return customer;
        } else {
            return null;
        }
    }
}
