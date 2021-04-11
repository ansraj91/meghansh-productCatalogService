package com.controller;


import com.service.ProductService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ContextConfiguration(classes = {ProductCatalog.class, ProductService.class})
@EnableJpaRepositories("com.repository")
@EntityScan("com.entity")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductCatalogTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void testCreateProduct() throws Exception {
        System.out.println("------>checking---->"+getCreateProductData().toString());
        this.mvc.perform(MockMvcRequestBuilders.post("/product/add").contentType(MediaType.APPLICATION_JSON).content(getCreateProductData().toString()).accept(MediaType.ALL_VALUE) ).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testGetProductDetails() throws Exception {
        String testProductId = getCreateProductData().getString("id");
        this.mvc.perform(MockMvcRequestBuilders.get("/product/" + testProductId).contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) .andExpect(content().json(getCreateProductData().toString())) ;
    }

    @Test
    @Order(3)
    public void testGetProductList() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/product/list").contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) .andExpect(content().json("["+ getCreateProductData().toString() + "]"));
    }
    @Test
    @Order(4)
    public void testUpdateProduct() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.put("/product/update/") .contentType(MediaType.APPLICATION_JSON).content(getUpdateProductData().toString())) .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void testDeleteProduct() throws Exception {
        String testProductId = getCreateProductData().getString("id");
        this.mvc.perform(MockMvcRequestBuilders.delete("/product/" + testProductId)) .andExpect(status().isOk());
    }

    //test data utility methods
    public JSONObject getCreateProductData() throws JSONException {
        JSONObject createProductData = new JSONObject();
        createProductData.put("id", "1");
        createProductData.put("title", "test product 1");
        createProductData.put("desc", "test product 1");
        createProductData.put("imagePath","Image Path");
        createProductData.put("unitPrice",100.0);

        return createProductData;
    }

    public JSONObject getUpdateProductData() throws JSONException {
        JSONObject createProductData = new JSONObject();
        createProductData.put("id", "1");
        createProductData.put("unitPrice",100);
        createProductData.put("desc","test product 1 title updated");
        createProductData.put("imagePath","Image Path");
        createProductData.put("title", "test product 1 updated");
        return createProductData;
    }
}
