package com.lecaru;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lecaru.domain.model.product.ProductCreateDTO;
import com.lecaru.domain.model.restaurant.dto.AddressCreateDTO;
import com.lecaru.domain.model.restaurant.dto.RestaurantCreateDTO;
import com.lecaru.domain.model.subcategory.SubCategoryDTO;
import com.lecaru.service.ProductService;
import com.lecaru.service.RestaurantService;
import com.lecaru.service.SubCategoryService;

@Component
public class StartApp implements CommandLineRunner {

    @Autowired
    private RestaurantService service;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        var restaurant = new RestaurantCreateDTO(
                "Barra da Tijuca",
                LocalTime.of(11, 0),
                LocalTime.of(15, 0),
                LocalTime.of(11, 0),
                LocalTime.of(16,0),
                LocalTime.of(18, 0),
                LocalTime.of(23, 0),
                LocalTime.of(18, 0),
                LocalTime.of(0, 0),
                "99090-9090",
                new AddressCreateDTO(
                        "22640-102",
                        "Avenida das Américas",
                        "Barra da Tijuca",
                        "Rio de Janeiro",
                        "RJ",
                        "21",
                        4666
                ));
        service.save(restaurant);
        var subcategory = subCategoryService.save(new SubCategoryDTO(null,"Bovinas", 1L)).toDTO();
        subCategoryService.save(new SubCategoryDTO(null,"Suinas", 1L));
        subCategoryService.save(new SubCategoryDTO(null,"Brownies", 6L));
        subCategoryService.save(new SubCategoryDTO(null,"Pastéis", 2L));
        subCategoryService.save(new SubCategoryDTO(null,"Lasanhas", 3L));
        subCategoryService.save(new SubCategoryDTO(null,"Arroz", 4L));
        subCategoryService.save(new SubCategoryDTO(null,"Sucos", 5L));
        var product = productService.save(new ProductCreateDTO(
                "Filé Mignon",
                "Filé Mignon com fritas e arroz a piamontese",
                "src/assets/products/file-mignon.png",
                600,
                2,
                new BigDecimal("60.00"),
                "Carnes",
                subcategory.id()
        ));
        productService.save(new ProductCreateDTO(
                "Filé de Frango",
                "Filé Mignon com fritas e arroz a piamontese",
                "src/assets/products/file-mignon.png",
                600,
                2,
                new BigDecimal("60.00"),
                "Carnes",
                subcategory.id()
        ));
        System.out.println("---------------------------------------------");
        System.out.println(product.toString());
        System.out.println("---------------------------------------------");
    }
}
