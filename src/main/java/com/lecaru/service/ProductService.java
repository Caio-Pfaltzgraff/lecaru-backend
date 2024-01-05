package com.lecaru.service;

import com.lecaru.domain.model.product.Product;
import com.lecaru.domain.model.product.dto.ProductDTO;
import com.lecaru.domain.repository.ProductRepository;
import com.lecaru.infra.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements CrudService<Product, ProductDTO, UUID>{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryTypeService categoryTypeService;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(Product.class));
    }

    @Transactional
    public Product save(ProductDTO dto) {
        var categoryType = categoryTypeService.findById(dto.categoryTypeId());
        var product = new Product(null, dto.title(), dto.description(), dto.photo(), dto.size(), dto.serving(), dto.price(), dto.category(), categoryType);
        return productRepository.save(product);
    }

    @Transactional
    public Product update(UUID id, ProductDTO dto) {
        var productUpdated = findById(id);
        var categoryType = categoryTypeService.findById(dto.categoryTypeId());
        productUpdated.update(dto, categoryType);
        return productUpdated;
    }

    @Transactional
    public void delete(UUID id) {
        var product = findById(id);
        productRepository.delete(product);
    }
}
