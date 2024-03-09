package com.lecaru.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lecaru.service.HomeService;
import com.lecaru.utils.HomeData;

import io.swagger.v3.oas.annotations.tags.Tag;


@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v2/home")
@Tag(name = "V2 Home Controller", description = "Home Page Controller")
public class HomeController {

  @Autowired
  private HomeService homeService;
  
  @GetMapping
  public ResponseEntity<HomeData> getSugestionAndRestaurants() {
    var data = homeService.findRestaurantSuggestionsAndUnits();
    return ResponseEntity.ok(data);
  }

}
