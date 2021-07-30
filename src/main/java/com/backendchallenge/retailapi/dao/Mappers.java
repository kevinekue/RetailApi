package com.backendchallenge.retailapi.dao;

import com.backendchallenge.retailapi.model.Category;
import com.backendchallenge.retailapi.model.Customer;
import com.backendchallenge.retailapi.model.Order;
import com.backendchallenge.retailapi.model.Product;
import org.springframework.jdbc.core.RowMapper;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Ekue created on 5/10/2021
 */


public class Mappers {
    protected static final class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            Product p = new Product();
            p.setProductID(rs.getInt("ProductID"));
            p.setProductName(rs.getString("ProductName"));
            p.setPricePerUnit(rs.getDouble("UnitPrice"));
            p.setStockQuantity(rs.getDouble("StockQuantity"));
            p.setUnit(rs.getString("Unit"));
            return p;
        }
    }

    protected static final class CategoryMapper implements RowMapper<Category>{
        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category c = new Category();
            c.setCategory(rs.getString("Category"));
            return c;
        }
    }

    protected static final class OrderMapper implements RowMapper<Order>{
        @Override
        public Order mapRow(ResultSet rs, int i) throws SQLException{
            Order o = new Order();
            o.setOrderID(rs.getInt("OrderID"));
            o.setOrderDate(rs.getDate("OrderTimeStamp"));
            o.setOrderStatus(rs.getString("OrderStatus"));
            return o;
        }
    }

    protected static final class CustomerMapper implements RowMapper<Customer>{
        @Override
        public Customer mapRow(ResultSet rs, int i) throws SQLException{
            Customer c = new Customer();
            c.setCustomerID(rs.getInt("CustomerID"));
            c.setCustomerName(rs.getString("CustomerName"));
            return c;
        }
    }


}
