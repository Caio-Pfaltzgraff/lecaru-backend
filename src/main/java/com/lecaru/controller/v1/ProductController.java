package com.lecaru.controller.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lecaru.domain.model.product.ProductAdminReadDTO;
import com.lecaru.domain.model.product.ProductCreateDTO;
import com.lecaru.domain.model.product.ProductDTO;
import com.lecaru.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "V1 Products Controller", description = "Admin Product Access Controller.")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get All Products", description = "Return list of all registered Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully")
    })
    public ResponseEntity<List<ProductAdminReadDTO>> getAllProducts() {
        var dtos = new ArrayList<ProductAdminReadDTO>();
        productService.findAll().forEach((product) -> dtos.add(new ProductAdminReadDTO(product.getId(), product.getTitle())));

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Product", description = "Returns data from a Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Argument"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<ProductDTO> getProduct(@PathVariable UUID id) {
        var product = productService.findById(id);

        return ResponseEntity.ok(product.toDTO());
    }

    @PostMapping
    @Operation(summary = "Create a new Product", description = "Register a new Product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product registered successfully"),
            @ApiResponse(responseCode = "400", description = "Product not registered successfully"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductCreateDTO dto) {
        var product = productService.save(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product.toDTO());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Product", description = "Update a registered Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Product not updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductCreateDTO dto) {
        var productUpdated = productService.update(id, dto);
        return ResponseEntity.ok(productUpdated.toDTO());
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
