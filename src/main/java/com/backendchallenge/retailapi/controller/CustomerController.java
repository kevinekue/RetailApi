package com.backendchallenge.retailapi.controller;

import com.backendchallenge.retailapi.dao.CustomerDao;
import com.backendchallenge.retailapi.dao.ProductDao;
import com.backendchallenge.retailapi.model.Customer;
import com.backendchallenge.retailapi.model.Order;
import com.backendchallenge.retailapi.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kevin Ekue created on 5/10/2021
 */


@RestController public class CustomerController {
    private final CustomerDao customerDao;
    ObjectMapper objMapper = new ObjectMapper();

    @Inject
    public CustomerController(CustomerDao customerDao){this.customerDao=customerDao;}

    @GetMapping("api/Customers")
    public List<Customer> getCustomersList(){
        List<Customer> customersList = customerDao.getAllCustomers();
        return  customersList;
    }

    @PostMapping("/api/customers/create")
    public Customer createCustomer(@RequestBody String jsonString) throws JsonProcessingException {
        System.out.println(jsonString);
        Customer c = objMapper.readValue(jsonString, Customer.class);
        System.out.println("Test: " + c.getCustomerID() + " CustomerName " + c.getCustomerName());
        customerDao.createCustomer(c);
        return c;
    }
}
