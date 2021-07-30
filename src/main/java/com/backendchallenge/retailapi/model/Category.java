package com.backendchallenge.retailapi.model;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kevin Ekue created on 5/9/2021
 */

@Component
public class Category {
    private String Category;
//    List<Product> listOfProductsPerCategory;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

//    public List<Product> getListOfProductsPerCategory() {
//        return listOfProductsPerCategory;
//    }

//    public void setListOfProductsPerCategory(List<Product> listOfProductsPerCategory) {
//        this.listOfProductsPerCategory = listOfProductsPerCategory;
//    }

    public Category() {
    }
}
