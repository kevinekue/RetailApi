package com.backendchallenge.retailapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Ekue created on 5/9/2021
 */

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    int orderID;
    int customerID;
    Date orderDate; //TODO: CONSIDER REMOVING THIS IF WE ONLY GO WITH CURRENTTIME
    List<Map<Integer, Integer>> productsOrdered;
    String orderStatus; //TODO: REEVALUATE ENUMS OPTION

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Map<Integer, Integer>> getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(List<Map<Integer, Integer>> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order() {
    }
}
