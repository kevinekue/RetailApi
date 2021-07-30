package com.backendchallenge.retailapi.dao;

import com.backendchallenge.retailapi.model.Customer;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Ekue created on 5/9/2021
 */

public interface CustomerDao {
    public Customer createCustomer(Customer c);

    public List<Customer> getAllCustomers();

}
