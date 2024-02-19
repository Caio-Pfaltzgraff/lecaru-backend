package com.lecaru.controller.v1;

import java.net.URI;
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

import com.lecaru.domain.model.restaurant.dto.RestaurantAdminReadDTO;
import com.lecaru.domain.model.restaurant.dto.RestaurantCreateDTO;
import com.lecaru.domain.model.restaurant.dto.RestaurantDTO;
import com.lecaru.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/restaurants")
@Tag(name = "V1 Restaurants Controller", description = "Admin Restaurant Access Controller.")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    @Operation(summary = "Get All Restaurants", description = "Return list of all registered restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully")
    })
    public ResponseEntity<List<RestaurantAdminReadDTO>> getAll() {
        var list = new ArrayList<RestaurantAdminReadDTO>();
        restaurantService.findAll().forEach((restaurant) -> list.add(new RestaurantAdminReadDTO(restaurant.getId(), restaurant.getTitle())));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Restaurant", description = "Returns data from a restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Argument"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable UUID id) {
        var restaurant = restaurantService.findById(id);
        return ResponseEntity.ok(restaurant.toDTO());
    }

    @PostMapping
    @Operation(summary = "Create a new restaurant", description = "Register a new restaurant in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant registered successfully"),
            @ApiResponse(responseCode = "400", description = "Restaurant not registered successfully"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody @Valid RestaurantCreateDTO restaurant) {
        var restaurantSaved = restaurantService.save(restaurant);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(restaurantSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(restaurantSaved.toDTO());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update restaurant", description = "Update a registered restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant updated successfully"),
            @ApiResponse(responseCode = "400", description = "Restaurant not updated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "409", description = "Data integrity violation error")
    })
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable UUID id, @RequestBody @Valid RestaurantCreateDTO restaurant) {
        var restaurantUpdated = restaurantService.update(id, restaurant);
        return ResponseEntity.ok(restaurantUpdated.toDTO());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete restaurant", description = "Delete a registered restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
