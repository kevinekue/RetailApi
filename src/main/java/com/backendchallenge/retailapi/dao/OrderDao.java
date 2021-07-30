package com.backendchallenge.retailapi.dao;

import com.backendchallenge.retailapi.model.Order;
import com.backendchallenge.retailapi.model.Product;

import java.util.List;
import java.util.Map;

/**
 * @author Kevin Ekue created on 5/9/2021
 */

public interface OrderDao {
    public Order createOrder(Order order);

    public Order getOrderByOrderID(int OrderID);

    public List<Map<String, Object>> getOrderDetailsByOrderID(int OrderID);

    public List<Order> getAllOrders();

    public List<Map<String, Object>> getAllOrdersByCustomer(int CustomerId);

    public void updateOrderInfo(List<Map<Product, Integer>> productsOrdered);

}
