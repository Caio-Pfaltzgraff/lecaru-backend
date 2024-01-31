package com.lecaru.domain.model.subcategory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "tb_subcategories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long categoryId;

    public SubCategoryDTO toDTO() {
        return new SubCategoryDTO(id, name, categoryId);
    }

    public void update(SubCategory subCategory) {
        if(subCategory.name != null) {
            this.name = subCategory.getName();
        }
        if(subCategory.categoryId != null) {
            this.categoryId = subCategory.getCategoryId();
        }
    }
}
