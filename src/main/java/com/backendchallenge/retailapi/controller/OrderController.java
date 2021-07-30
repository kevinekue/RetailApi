package com.backendchallenge.retailapi.controller;

import com.backendchallenge.retailapi.dao.OrderDao;
import com.backendchallenge.retailapi.model.Order;
import com.backendchallenge.retailapi.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Ekue created on 5/10/2021
 */

//TODO: Update Order Status, Modify Stock Quantity upon Order


@RestController
public class OrderController {
    private OrderDao orderDao;
    ObjectMapper objMapper = new ObjectMapper();

    @Inject
    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @GetMapping("/api/orders/order")
    @ResponseBody
    public List<Map<String, Object>> displayOrderDetails(@RequestParam int orderId) {
        List<Map<String, Object>> orderDetails = orderDao.getOrderDetailsByOrderID(orderId);
        return orderDetails;
    }

    @GetMapping("/api/orders")
    public List<Order> displayOrders() {
        List<Order> orderLists = orderDao.getAllOrders();
        return orderLists;
    }

    @GetMapping("/api/orders/customer")
    public List<Map<String, Object>> displayCustomerOrdersDetails(@RequestParam int customerId) {
        List<Map<String, Object>> orderDetails = orderDao.getAllOrdersByCustomer(customerId);
        return orderDetails;
    }

    @PostMapping("/api/orders/create")
    public Order createOrder(@RequestBody String jsonString) throws JsonProcessingException {
        System.out.println(jsonString);
        Order o = objMapper.readValue(jsonString, Order.class);
        System.out.println("Test: " + o.getOrderID() + " CustomerID next " + o.getCustomerID() + " Date:" + o.getOrderDate() + " " + o.getOrderStatus());
        orderDao.createOrder(o);
        return o;
    }

}
