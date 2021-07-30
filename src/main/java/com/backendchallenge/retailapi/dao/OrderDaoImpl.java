package com.backendchallenge.retailapi.dao;

import com.backendchallenge.retailapi.model.Order;
import com.backendchallenge.retailapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Kevin Ekue created on 5/9/2021
 */

@Component
public class OrderDaoImpl implements OrderDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //   TODO: PREPARED STATEMENTS
    private static final String SQL_SELECT_ALL_ORDERS
            = "select * from `Order` order by `Order`.OrderTimestamp;";

    private static final String SQL_SELECT_ALL_ORDERS_BY_CUSTOMERID
            = "select ProductID, Quantity, `Order`.OrderID, `Order`.OrderTimestamp, `Order`.OrderStatus  from `Order` inner join OrderInfo on OrderInfo.OrderID=`Order`.CustomerID where CustomerID = ? order by `Order`.OrderTimestamp;";

    private static final String SQL_SELECT_ORDER_BY_ORDERID
            = "select * from `OrderInfo` where OrderID=?";

    private static final String SQL_SELECT_PRODUCT_DETAILS_IN_ORDERID
            = "select ProductID, Quantity from OrderInfo where OrderInfo.OrderID= ?";

    private static final String SQL_CREATE_ORDER = " insert into `Order`( CustomerID, OrderTimestamp, OrderStatus)  " +
            "values 	(?, ?, ?);";

    private static final String SQL_CREATE_ORDER_DETAILS = "insert into OrderInfo(OrderID, ProductID, Quantity, UnitPrice)" +
            "values (?, ?, ?, ?);";
    //TODO: UPDATE VALUE OF PRODUCT QUANTITY

    @Override
    public Order createOrder(Order order) {
        jdbcTemplate.update(SQL_CREATE_ORDER,
                order.getCustomerID(),
                order.getOrderDate(),
                order.getOrderStatus());

        order.setOrderID(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

        for (Map<Integer, Integer> mapEntry : order.getProductsOrdered()) {
            Set<Integer> productKeySet = mapEntry.keySet();
            Integer productKey = null;
            for (Integer randomKey : productKeySet) {
                productKey = randomKey;
            }

            //TODO: CHECK PRODUCT STOCK AND UPDATE THE QUANTITY. IF SALE IMPOSSIBLE, SEND OUT OF STOCK EXCEPTION

            Product product = productDao.getProductById(productKey);
            jdbcTemplate.update(SQL_CREATE_ORDER_DETAILS,
                    order.getOrderID(),
                    productKey,
                    mapEntry.get(productKey),
                    product.getPricePerUnit());
        }

        return order;
    }

    @Inject
    ProductDao productDao;

    @Override
    public Order getOrderByOrderID(int orderID) {
        Order o = new Order();
        o.setOrderID(orderID);
        Object test = jdbcTemplate.query(SQL_SELECT_ORDER_BY_ORDERID, new Mappers.OrderMapper(), orderID);

        return o;
    }

    @Override
    public List<Map<String, Object>> getOrderDetailsByOrderID(int orderID) {
        //TODO: LOOK INTO REFACTORING THIS CODE

        System.out.println("Order ID " + orderID);
        List<Map<String, Object>> oDetails = new ArrayList<>();


        oDetails = jdbcTemplate.queryForList(SQL_SELECT_PRODUCT_DETAILS_IN_ORDERID, orderID);
        System.out.println(oDetails.toString());


        return oDetails;
    }

    List<Map<Integer, Integer>> getOrderDetailsByOrderID2(int orderID) {
        List<Map<Integer, Integer>> oDetails2 = new ArrayList<>();
        oDetails2 = jdbcTemplate.query(SQL_SELECT_PRODUCT_DETAILS_IN_ORDERID, new RowMapper<Map<Integer, Integer>>() {
            @Override
            public Map<Integer, Integer> mapRow(ResultSet rs, int i) throws SQLException, DataAccessException {
                Map<Integer, Integer> orderDetailsMap = new HashMap<>();
                orderDetailsMap.put(rs.getInt("ProductID"), rs.getInt("Quantity"));

                return orderDetailsMap;
            }
        }, orderID);
        System.out.println(oDetails2.toString());

        return oDetails2;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> ordersList = jdbcTemplate.query(SQL_SELECT_ALL_ORDERS, new Mappers.OrderMapper());
        for (Order o : ordersList) {
            o.setProductsOrdered(getOrderDetailsByOrderID2(o.getOrderID()));
        }
        return ordersList;
    }

    @Override
    public List<Map<String, Object>> getAllOrdersByCustomer(int customerId) {

//    public List<Order> getAllOrdersByCustomer(int customerId) {
//        List<Order> ordersList = jdbcTemplate.query(SQL_SELECT_ALL_ORDERS_BY_CUSTOMERID, new Mappers.OrderMapper(), customerId);
//        for(Order o: ordersList){
//            o.setProductsOrdered(getOrderDetailsByOrderID2(o.getOrderID()));
//        }
//        return null;
        List<Map<String, Object>> ordersList = jdbcTemplate.queryForList(SQL_SELECT_ALL_ORDERS_BY_CUSTOMERID, customerId);
        return ordersList;
    }

    @Override
    public void updateOrderInfo(List<Map<Product, Integer>> productsOrdered) {

    }

}
