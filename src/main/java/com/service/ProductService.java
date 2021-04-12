package com.service;

import com.entity.Product;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(){}

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public void deleteById(String id){
        productRepository.deleteById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
    public Optional<Product> findById(String id){
        return productRepository.findById(id);
    }
    @Transactional
    public int update(String id, Product product){
       return productRepository.updateProductRepo(id,product.getTitle(),product.getUnitPrice(),product.getImagePath(), product.getDesc());
    }
    @Transactional
    public int reduceQuantityBy(String id, int qty){
        return productRepository.reduceQuantity(id,qty);
    }
}
