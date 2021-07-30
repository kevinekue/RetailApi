package com.backendchallenge.retailapi.dao;

/**
 * @author Kevin Ekue created on 5/10/2021
 */


public class RetailAPIQueries {
    private static final String SQL_ADD_PRODUCT = "insert into `Product`(ProductName, StockQuantity, UnitPrice)" +
            "values(?,?,?)";
    private static final String SQL_SELECT_ALL_PRODUCTS
            = "select * from Product";
}
