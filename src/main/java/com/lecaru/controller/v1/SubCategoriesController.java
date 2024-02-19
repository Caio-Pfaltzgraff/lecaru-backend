package com.lecaru.controller.v1;

import java.util.ArrayList;
import java.util.List;

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

import com.lecaru.domain.model.subcategory.SubCategoryAdminReadDTO;
import com.lecaru.domain.model.subcategory.SubCategoryDTO;
import com.lecaru.service.SubCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
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
    public ResponseEntity<List<SubCategoryAdminReadDTO>> getAll() {
        var list = new ArrayList<SubCategoryAdminReadDTO>();
        
       subCategoryService.findAll().forEach((subCategory) -> list.add(new SubCategoryAdminReadDTO((subCategory.getId()), subCategory.getTitle(), subCategory.getCategoryId())));

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get SubCategory", description = "Admin Subcategories Access Controller.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Argument"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<SubCategoryDTO> getType(@PathVariable Long id) {
        var subcategory = subCategoryService.findById(id).toDTO();
        return ResponseEntity.ok(subcategory);
    }

    @PostMapping
    @Operation(summary = "Create a new SubCategory", description = "Register a new SubCategory in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SubCategory registered successfully"),
            @ApiResponse(responseCode = "400", description = "SubCategory not registered successfully"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<SubCategoryDTO> createCategoryType(@RequestBody @Valid SubCategoryDTO dto) {
        var categoryType = subCategoryService.save(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryType.getId())
                .toUri();
        return ResponseEntity.created(location).body(categoryType.toDTO());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update SubCategory", description = "Update a registered SubCategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SubCategory updated successfully"),
            @ApiResponse(responseCode = "400", description = "SubCategory not updated successfully"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<SubCategoryDTO> updateCategoryType(@PathVariable Long id, @RequestBody @Valid SubCategoryDTO dto) {
        var categoryTypeUpdated = subCategoryService.update(id, dto).toDTO();
        return ResponseEntity.ok(categoryTypeUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete SubCategory", description = "Delete a registered SubCategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "SubCategory deleted successfully"),
            @ApiResponse(responseCode = "409", description = "SubCategory cannot be deleted"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<Void> deleteCategoryType(@PathVariable Long id) {
        subCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
