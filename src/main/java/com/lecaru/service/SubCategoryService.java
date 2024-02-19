package com.lecaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lecaru.domain.model.subcategory.SubCategory;
import com.lecaru.domain.model.subcategory.SubCategoryDTO;
import com.lecaru.domain.repository.SubCategoryRepository;
import com.lecaru.infra.exception.NotFoundException;

@Service
public class SubCategoryService implements CrudService<SubCategory, SubCategoryDTO, Long>{

    @Autowired
    private SubCategoryRepository categoryTypeRepository;

    @Transactional(readOnly = true)
    public List<SubCategory> findAll() {
        return categoryTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SubCategory findById(Long id) {
        return categoryTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(SubCategory.class));
    }

    @Transactional
    @CacheEvict(value = "subcategory", allEntries = true)
    public SubCategory save(SubCategoryDTO dto) {
        return categoryTypeRepository.save(new SubCategory(null, dto.title(), dto.categoryId()));
    }
    
    @Transactional
    @CacheEvict(value = "subcategory", allEntries = true)
    public SubCategory update(Long id, SubCategoryDTO dto) {
        var categoryType = findById(id);
        categoryType.update(new SubCategory(id, dto.title(), dto.categoryId()));
        return categoryType;
    }
    
    @Transactional
    @CacheEvict(value = "subcategory", allEntries = true)
    public void delete(Long id) {
        var categoryType = findById(id);
        categoryTypeRepository.delete(categoryType);
    }
}
