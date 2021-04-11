package com.repository;

import com.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
    @Modifying(clearAutomatically = true )
    @Query("update Product p set p.title = :title, p.desc=:desc, p.imagePath=:imagePath,p.unitPrice=:unitPrice where p.id = :id")
    int updateProductRepo(@Param("id") String id, @Param("title") String title, @Param("unitPrice") double unitPrice, @Param("imagePath") String imagePath, @Param("desc") String desc);
}
