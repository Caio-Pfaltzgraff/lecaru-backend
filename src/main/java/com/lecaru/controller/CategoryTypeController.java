package com.lecaru.controller;

import com.lecaru.domain.model.product.CategoryType;
import com.lecaru.domain.model.product.dto.CategoryTypeDTO;
import com.lecaru.service.CategoryTypeService;
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

@RestController
@RequestMapping("/types")
@Tag(name = "Category Types Controller", description = "Rest-Controller for category types access.")
public class CategoryTypeController {

    @Autowired
    private CategoryTypeService categoryTypeService;

    @GetMapping
    @Operation(summary = "Get all Category Types", description = "Return list of all registered Category Types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully")
    })
    public ResponseEntity<List<CategoryType>> getAll() {
        return ResponseEntity.ok(categoryTypeService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Category Type", description = "Returns data from a Category Type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Argument"),
            @ApiResponse(responseCode = "404", description = "Category type not found")
    })
    public ResponseEntity<CategoryType> getType(@PathVariable Long id) {
        return ResponseEntity.ok(categoryTypeService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new Category Type", description = "Register a new Category Type in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category Type registered successfully"),
            @ApiResponse(responseCode = "400", description = "Category Type not registered successfully"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<CategoryType> createCategoryType(@RequestBody @Valid CategoryTypeDTO dto) {
        var categoryType = categoryTypeService.save(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryType.getId())
                .toUri();
        return ResponseEntity.created(location).body(categoryType);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Category Type", description = "Update a registered Category Type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Type updated successfully"),
            @ApiResponse(responseCode = "400", description = "Category Type not updated successfully"),
            @ApiResponse(responseCode = "404", description = "Category Type not found"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<CategoryType> updateCategoryType(@PathVariable Long id, @RequestBody @Valid CategoryTypeDTO dto) {
        var categoryTypeUpdated = categoryTypeService.update(id, dto);
        return ResponseEntity.ok(categoryTypeUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Category Type", description = "Delete a registered Category Type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category Type deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category Type not found")
    })
    public ResponseEntity<Void> deleteCategoryType(@PathVariable Long id) {
        categoryTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
