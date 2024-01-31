package com.lecaru.service;

import com.lecaru.domain.model.subcategory.SubCategory;
import com.lecaru.domain.model.subcategory.SubCategoryDTO;
import com.lecaru.domain.repository.SubCategoryRepository;
import com.lecaru.infra.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public SubCategory save(SubCategoryDTO dto) {
        return categoryTypeRepository.save(new SubCategory(null, dto.name(), dto.categoryId()));
    }

    @Transactional
    public SubCategory update(Long id, SubCategoryDTO dto) {
        var categoryType = findById(id);
        categoryType.update(new SubCategory(id, dto.name(), dto.categoryId()));
        return categoryType;
    }

    @Transactional
    public void delete(Long id) {
        var categoryType = findById(id);
        categoryTypeRepository.delete(categoryType);
    }
}
