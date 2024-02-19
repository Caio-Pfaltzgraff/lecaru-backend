package com.lecaru.domain.model.product;

import java.math.BigDecimal;
import java.util.UUID;

import com.lecaru.domain.model.subcategory.SubCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public ProductDTO toDTO() {
        return new ProductDTO(id, title, description, photo, size, serving, price, category, subCategory);
    }

    public void update(ProductCreateDTO dto, SubCategory subCategory) {
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
