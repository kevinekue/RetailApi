package com.backendchallenge.retailapi.dao;

import com.backendchallenge.retailapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin Ekue created on 5/9/2021
 */

@Component
public class CustomerDaoImpl implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_ALL_CUSTOMERS
            = "select * from Customer";

    private static final String SQL_CREATE_CUSTOMER
            = "insert into Customer(CustomerName) values (?)";

    @Override
    public Customer createCustomer(Customer c) {
        jdbcTemplate.update(SQL_CREATE_CUSTOMER, c.getCustomerName());
        c.setCustomerID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return c;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customersList = jdbcTemplate.query(SQL_SELECT_ALL_CUSTOMERS, new Mappers.CustomerMapper());

        return customersList;
    }
}
