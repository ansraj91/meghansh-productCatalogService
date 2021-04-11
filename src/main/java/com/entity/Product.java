package com.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Data
@Entity
@Table(name="product", schema = "product_schema")
public class Product {
    @Id
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NonNull
    @Column
    private String title;

    @Column(name="description")
    private String desc;

    @NotNull
    @Column
    private String imagePath;

    //@Min(value=1)
    @Column
    private double unitPrice;

    public Product(){}
    @Override
    public boolean equals(Object object){
        if(this==object) return true;
        if(object==null || getClass()!=object.getClass()) return false;

        Product that = (Product) object;
        return Objects.equals(this.id,that.id)
                && Objects.equals(this.title,that.title)
                && Objects.equals(this.unitPrice,that.unitPrice)
                && Objects.equals(this.desc,that.desc)
                && Objects.equals(this.imagePath,that.imagePath);
     }

     @Override
    public int hashCode(){
        return Objects.hash(id,title,unitPrice,desc,imagePath);
     }

}
