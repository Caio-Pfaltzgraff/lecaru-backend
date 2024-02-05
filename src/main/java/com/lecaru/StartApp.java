package com.lecaru;

import com.lecaru.domain.model.subcategory.SubCategoryDTO;
import com.lecaru.domain.model.product.ProductDTO;
import com.lecaru.domain.model.restaurant.dto.AddressDTO;
import com.lecaru.domain.model.restaurant.dto.RestaurantDTO;
import com.lecaru.service.ProductService;
import com.lecaru.service.RestaurantService;
import com.lecaru.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;

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
        var restaurant = new RestaurantDTO(
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
                new AddressDTO(
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
        var subcategory2 = subCategoryService.save(new SubCategoryDTO(null,"Suinas", 1L));
        var subcategory3 = subCategoryService.save(new SubCategoryDTO(null,"Brownies", 6L));
        var subcategory4 = subCategoryService.save(new SubCategoryDTO(null,"Pastéis", 2L));
        var subcategory5 = subCategoryService.save(new SubCategoryDTO(null,"Lasanhas", 3L));
        var subcategory6 = subCategoryService.save(new SubCategoryDTO(null,"Arroz", 4L));
        var subcategory7 = subCategoryService.save(new SubCategoryDTO(null,"Sucos", 5L));
        var product = productService.save(new ProductDTO(
                "Filé Mignon",
                "Filé Mignon com fritas e arroz a piamontese",
                "src/assets/products/file-mignon.png",
                600,
                2,
                new BigDecimal("60.00"),
                "Carnes",
                subcategory.id()
        ));
        var product2 = productService.save(new ProductDTO(
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
