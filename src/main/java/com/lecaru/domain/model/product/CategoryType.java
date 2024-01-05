package com.lecaru.domain.model.product;

import com.lecaru.domain.model.product.dto.CategoryTypeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "tb_category_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long categoryId;

    public CategoryTypeDTO toDTO() {
        return new CategoryTypeDTO(id, name, categoryId);
    }

    public void update(CategoryType categoryType) {
        if(categoryType.name != null) {
            this.name = categoryType.getName();
        }
        if(categoryType.categoryId != null) {
            this.categoryId = categoryType.getCategoryId();
        }
    }
}
