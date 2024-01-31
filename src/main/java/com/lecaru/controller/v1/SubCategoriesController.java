package com.lecaru.controller.v1;

import com.lecaru.domain.model.subcategory.SubCategory;
import com.lecaru.domain.model.subcategory.SubCategoryDTO;
import com.lecaru.service.SubCategoryService;
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
@RequestMapping("/api/v1/subcategories")
@Tag(name = "V1 SubCategory Controller", description = "Rest-Controller for subcategories access.")
public class SubCategoriesController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping
    @Operation(summary = "Get all SubCategories", description = "Return list of all registered Category Types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully")
    })
    public ResponseEntity<List<SubCategory>> getAll() {
        return ResponseEntity.ok(subCategoryService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get SubCategory", description = "Admin Subcategories Access Controller.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Argument"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<SubCategory> getType(@PathVariable Long id) {
        return ResponseEntity.ok(subCategoryService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new SubCategory", description = "Register a new SubCategory in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SubCategory registered successfully"),
            @ApiResponse(responseCode = "400", description = "SubCategory not registered successfully"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<SubCategory> createCategoryType(@RequestBody @Valid SubCategoryDTO dto) {
        var categoryType = subCategoryService.save(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryType.getId())
                .toUri();
        return ResponseEntity.created(location).body(categoryType);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update SubCategory", description = "Update a registered SubCategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SubCategory updated successfully"),
            @ApiResponse(responseCode = "400", description = "SubCategory not updated successfully"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<SubCategory> updateCategoryType(@PathVariable Long id, @RequestBody @Valid SubCategoryDTO dto) {
        var categoryTypeUpdated = subCategoryService.update(id, dto);
        return ResponseEntity.ok(categoryTypeUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete SubCategory", description = "Delete a registered SubCategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "SubCategory deleted successfully"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<Void> deleteCategoryType(@PathVariable Long id) {
        subCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}