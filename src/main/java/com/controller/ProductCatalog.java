package com.controller;

import com.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/product")
public class ProductCatalog {
    private static Map<String, Product> productCatalog = new HashMap<>();

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product){
        productCatalog.put(product.getId(),product);
        return "Product Added Successfully.";
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id){
        return productCatalog.get(id);
    }

    @GetMapping("/list")
    public List<Product> getProductList(){
        return new ArrayList<>(productCatalog.values());
    }

    @PutMapping("/update")
    public String updateProduct(@RequestBody Product product){
        productCatalog.put(product.getId(),product);
        return "Product Updated successfull.";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id){
        productCatalog.remove(id);
        return "Product Removed Successfully.";
    }
}
