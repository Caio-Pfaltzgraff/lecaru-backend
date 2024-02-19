package com.lecaru.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lecaru.domain.model.product.Product;
import com.lecaru.domain.model.product.ProductCreateDTO;
import com.lecaru.domain.repository.ProductRepository;
import com.lecaru.infra.exception.NotFoundException;

@Service
public class ProductService implements CrudService<Product, ProductCreateDTO, UUID>{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubCategoryService subCategoryService;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(Product.class));
    }

    @Transactional
    public Product save(ProductCreateDTO dto) {
        var categoryType = subCategoryService.findById(dto.subCategoryId());
        var product = new Product(null, dto.title(), dto.description(), dto.photo(), dto.size(), dto.serving(), dto.price(), dto.category(), categoryType);
        return productRepository.save(product);
    }

    @Transactional
    public Product update(UUID id, ProductCreateDTO dto) {
        var productUpdated = findById(id);
        var categoryType = subCategoryService.findById(dto.subCategoryId());
        productUpdated.update(dto, categoryType);
        return productUpdated;
    }

    @Transactional
    public void delete(UUID id) {
        var product = findById(id);
        productRepository.delete(product);
    }
}
