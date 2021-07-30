package com.backendchallenge.retailapi.model;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kevin Ekue created on 5/9/2021
 */

@Component
public class Customer {
    int customerID;
    String customerName;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    

    public Customer() {
    }
}
