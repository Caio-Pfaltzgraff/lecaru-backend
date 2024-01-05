package com.lecaru.service;

import com.lecaru.domain.model.product.CategoryType;
import com.lecaru.domain.model.product.dto.CategoryTypeDTO;
import com.lecaru.domain.repository.CategoryTypeRepository;
import com.lecaru.infra.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryTypeService implements CrudService<CategoryType, CategoryTypeDTO, Long>{

    @Autowired
    private CategoryTypeRepository categoryTypeRepository;

    @Transactional(readOnly = true)
    public List<CategoryType> findAll() {
        return categoryTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryType findById(Long id) {
        return categoryTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(CategoryType.class));
    }

    @Transactional
    public CategoryType save(CategoryTypeDTO dto) {
        return categoryTypeRepository.save(new CategoryType(null, dto.name(), dto.categoryId()));
    }

    @Transactional
    public CategoryType update(Long id, CategoryTypeDTO dto) {
        var categoryType = findById(id);
        categoryType.update(new CategoryType(id, dto.name(), dto.categoryId()));
        return categoryType;
    }

    @Transactional
    public void delete(Long id) {
        var categoryType = findById(id);
        categoryTypeRepository.delete(categoryType);
    }
}
