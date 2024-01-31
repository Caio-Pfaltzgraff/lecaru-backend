package com.lecaru.domain.model.product;

import com.lecaru.domain.model.subcategory.SubCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "tb_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id @GeneratedValue(generator = "UUID")
    private UUID id;
    private String title;
    private String description;
    private String photo;
    private Integer size;
    private Integer serving;
    private BigDecimal price;
    private String category;
    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory;

    public void update(ProductDTO dto, SubCategory subCategory) {
        if(dto.title() != null) {
            this.title = dto.title();
        }
        if(dto.description() != null) {
            this.description = dto.description();
        }
        if(dto.photo() != null) {
            this.photo = dto.photo();
        }
        if(dto.size() != null) {
            this.size = dto.size();
        }
        if(dto.serving() != null) {
            this.serving = dto.serving();
        }
        if(dto.price() != null) {
            this.price = dto.price();
        }
        if(dto.category() != null) {
            this.category = dto.category();
        }
        if(subCategory != null) {
            this.subCategory = subCategory;
        }
    }
}
