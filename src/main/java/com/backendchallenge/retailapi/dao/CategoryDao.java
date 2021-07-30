package com.backendchallenge.retailapi.dao;

import com.backendchallenge.retailapi.model.Category;
import com.backendchallenge.retailapi.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * @author Kevin Ekue created on 5/11/2021
 */

public interface CategoryDao {
    public Category createCategory(Category category);
    public List<Category> getAllCategories();
    public List<Map<String, Object>> getProductsByCategory(String category);
}
