package com.backendchallenge.retailapi.dao;

import com.backendchallenge.retailapi.model.Product;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kevin Ekue created on 5/9/2021
 */

public interface ProductDao {
    public Product addProduct(Product product);
    public List<Product> getAllProducts();
    public List<Map<String, Object>> getAllProductsSoldByQuantity();
    public List<Map<String, Object>> getAllProductsSoldByQuantityDateRange(Date startDate, Date endDate);
    public List<Map<String, Object>> getAllProductsSoldByQuantityDay(Date startDate, Date endDate, Date refDate);
    public List<Map<String, Object>> getAllProductsSoldByQuantityWeek(Date startDate, Date endDate, Date refDate);
    public List<Map<String, Object>> getAllProductsSoldByQuantityMonth(Date startDate, Date endDate, Date refDate);
    public Product getProductById(int productId);
    public void updateProduct (Product product);
    public void deleteProduct (long productId);
}
