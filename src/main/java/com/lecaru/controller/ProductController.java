package com.lecaru.controller;

import com.lecaru.domain.model.product.Product;
import com.lecaru.domain.model.product.dto.ProductDTO;
import com.lecaru.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@Tag(name = "Products Controller", description = "Rest-Controller for Products access.")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get All Products", description = "Return list of all registered Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully")
    })
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Product", description = "Returns data from a Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Argument"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new Product", description = "Register a new Product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product registered successfully"),
            @ApiResponse(responseCode = "400", description = "Product not registered successfully"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO dto) {
        var product = productService.save(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Product", description = "Update a registered Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Product not updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductDTO dto) {
        var productUpdated = productService.update(id, dto);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product", description = "Delete a registered Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
