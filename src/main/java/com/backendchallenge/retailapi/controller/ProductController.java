package com.backendchallenge.retailapi.controller;

import com.backendchallenge.retailapi.dao.ProductDao;
import com.backendchallenge.retailapi.model.Order;
import com.backendchallenge.retailapi.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Kevin Ekue created on 5/10/2021
 */

//@Controller
@RestController
public class ProductController {
    private final ProductDao productDao;
    ObjectMapper objMapper = new ObjectMapper();

    @Inject
    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }

    @GetMapping("api/products")
    public List<Product>  displayProductsList(){
        List<Product>  productsList = productDao.getAllProducts();
        System.out.println(productsList.toString());
        return productsList;
    }

    @GetMapping("api/products/sold")
    public List<Map<String, Object>>  displayProductsSoldList(){
        List<Map<String, Object>>  productsList = productDao.getAllProductsSoldByQuantity();
        System.out.println(productsList.toString());
        return productsList;
    }


//    TODO: TEST FOR BAD ENTRY,  OR OUT OF ORDER DATES
    @GetMapping("api/products/sold/daterange")
    public List<Map<String, Object>>  displayProductsListDateRange(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date refDate,
                                                                   @RequestParam String resultType){

        switch (resultType.toLowerCase()){
            case "day":
                return productDao.getAllProductsSoldByQuantityDay(startDate, endDate, refDate);
            case "week":
                return  productDao.getAllProductsSoldByQuantityWeek(startDate, endDate, refDate);
            case "month":
                return productDao.getAllProductsSoldByQuantityMonth(startDate,endDate,refDate);
            default:
                if(refDate == null){
                    return productDao.getAllProductsSoldByQuantityWeek(startDate, endDate, endDate);
                }
                return productDao.getAllProductsSoldByQuantityDateRange(startDate, endDate);
        }
    }

    @GetMapping("api/products/product")
    public Product displayProductById(@RequestParam int productId){
        System.out.println("Let's try this!");
        Product p = productDao.getProductById(productId);

        return p;
    }

    @PostMapping("/api/products/create")
    public Product createOrder(@RequestBody String jsonString) throws JsonProcessingException {
        System.out.println(jsonString);
        Product p = objMapper.readValue(jsonString, Product.class);
        productDao.addProduct(p);
        return p;
    }
}
