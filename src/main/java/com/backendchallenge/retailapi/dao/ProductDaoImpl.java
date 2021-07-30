package com.backendchallenge.retailapi.dao;

import com.backendchallenge.retailapi.model.Product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Kevin Ekue created on 5/9/2021
 */

@Component
public class ProductDaoImpl implements ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_PRODUCT = "insert into `Product`(ProductName, StockQuantity, UnitPrice)" +
            "values(?,?,?)";
    private static final String SQL_SELECT_ALL_PRODUCTS
            = "select * from Product";

    private static final String SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY = "select ProductID, Quantity, `OrderInfo`.OrderID, `Order`.OrderTimestamp from orderinfo inner join `Order` on OrderInfo.OrderID = `Order`.OrderID order by Quantity desc;";

    private static final String SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY_DATE_RANGE
            = "select ProductID, Quantity, `OrderInfo`.OrderID, `Order`.OrderTimestamp from orderinfo inner join `Order` on OrderInfo.OrderID = `Order`.OrderID where OrderTimestamp >= ? and OrderTimestamp <= ? order by Quantity desc;";

    private static final String SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY_DAY
            = "select ProductID, Quantity, `OrderInfo`.OrderID, `Order`.OrderTimestamp from orderinfo inner join `Order` on OrderInfo.OrderID = `Order`.OrderID where OrderTimestamp >= ? and OrderTimestamp <= ? and day(OrderTimestamp)=day(?) order by Quantity desc;";

    private static final String SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY_WEEK
            = "select ProductID, Quantity, `OrderInfo`.OrderID, `Order`.OrderTimestamp from orderinfo inner join `Order` on OrderInfo.OrderID = `Order`.OrderID where OrderTimestamp >= ? and OrderTimestamp <= ? and week(OrderTimestamp)=week(?) order by Quantity desc;";

    private static final String SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY_MONTH
            = "select ProductID, Quantity, `OrderInfo`.OrderID, `Order`.OrderTimestamp from orderinfo inner join `Order` on OrderInfo.OrderID = `Order`.OrderID where OrderTimestamp >= ? and OrderTimestamp <= ? and month(OrderTimestamp)=month(?) order by Quantity desc;";

    private static final String SQL_SELECT_PRODUCTS_BY_PRODUCTID
            = "select * from Product where ProductID=?";

    private static final String GET_PRODUCT_CATEGORIES = "select Category from ProductInfo where ProductID = ?";


    @Override
    public Product addProduct(Product p) {
        jdbcTemplate.update(SQL_ADD_PRODUCT,
                p.getProductName(),
                p.getStockQuantity(),
                p.getPricePerUnit());
        p.setProductID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        return p;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productsList = jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS, new Mappers.ProductMapper());
        for (Product p : productsList) {
            int productId = p.getProductID();
            p.setCategory(jdbcTemplate.query(GET_PRODUCT_CATEGORIES, new Mappers.CategoryMapper(), productId));
        }
        return productsList;
    }

    @Override
    public List<Map<String, Object>> getAllProductsSoldByQuantity() {
        List<Map<String, Object>> productsList = jdbcTemplate.queryForList(SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY);
        return productsList;
    }

    @Override
    public List<Map<String, Object>> getAllProductsSoldByQuantityDateRange(Date startDate, Date endDate) {
        List<Map<String, Object>> productsList = jdbcTemplate.queryForList(SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY_DATE_RANGE, startDate, endDate);
        return productsList;
    }

    @Override
    public List<Map<String, Object>> getAllProductsSoldByQuantityDay(Date startDate, Date endDate, Date refDate) {
        List<Map<String, Object>> productsList = jdbcTemplate.queryForList(SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY_DAY, startDate, endDate, refDate);
        return productsList;
    }

    @Override
    public List<Map<String, Object>> getAllProductsSoldByQuantityWeek(Date startDate, Date endDate, Date refDate) {
        List<Map<String, Object>> productsList = jdbcTemplate.queryForList(SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY_WEEK, startDate, endDate, refDate);
        return productsList;
    }

    @Override
    public List<Map<String, Object>> getAllProductsSoldByQuantityMonth(Date startDate, Date endDate, Date refDate) {
        List<Map<String, Object>> productsList = jdbcTemplate.queryForList(SQL_SELECT_ALL_PRODUCTS_SOLD_SORT_QTY_MONTH, startDate, endDate, refDate);
        return productsList;
    }




    @Override
    public Product getProductById(int productId) {
        Product p = jdbcTemplate.queryForObject(SQL_SELECT_PRODUCTS_BY_PRODUCTID, new Mappers.ProductMapper(), productId);
        p.setCategory(jdbcTemplate.query(GET_PRODUCT_CATEGORIES, new Mappers.CategoryMapper(), productId));
        return p;
    }


    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(long productId) {

    }


}
