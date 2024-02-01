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
    private String title;
    private Long categoryId;

    public SubCategoryDTO toDTO() {
        return new SubCategoryDTO(id, title, categoryId);
    }

    public void update(SubCategory subCategory) {
        if(subCategory.title != null) {
            this.title = subCategory.getTitle();
        }
        if(subCategory.categoryId != null) {
            this.categoryId = subCategory.getCategoryId();
        }
    }
}
