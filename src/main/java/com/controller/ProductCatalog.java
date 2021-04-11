package com.controller;

import com.entity.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping("/product")
public class ProductCatalog {
   // private static Map<String, Product> productCatalog = new HashMap<>();

    @Autowired
    private ProductService productService;

    @PostMapping(path="/add",consumes = "application/json", produces = "application/json")
    public @ResponseBody Product addProduct(@RequestBody Product product){
        System.out.println(product.toString());
       return productService.save(product);
    }

    @GetMapping(path="/{id}",produces = "application/json")
    public Product getProductById(@PathVariable String id){
        return productService.findById(id).orElse(null);
    }

    @GetMapping(path="/list", produces = "application/json")
    public Iterable<Product> getProductList(){
        return productService.findAll();
    }

    @PutMapping(path="/update", consumes = "application/json", produces = "application/json")
    public int updateProduct(@RequestBody Product product){
       return productService.update(product.getId(),product);

    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id){
        productService.deleteById(id);
        return "Product Removed Successfully.";
    }
    @GetMapping("/customHealth")
    public String healthCheck(){
        return "Server is working";
    }
}
