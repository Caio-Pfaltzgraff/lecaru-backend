package com.lecaru;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.lecaru.domain.model.restaurant.Address;
import com.lecaru.domain.model.restaurant.Restaurant;
import com.lecaru.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class StartApp implements CommandLineRunner {

    @Autowired
    private RestaurantService service;

    @Override
    public void run(String... args) throws Exception {
        var restaurant = new Restaurant(
                null, "Barra Tijuca",
                LocalTime.of(11, 0),
                LocalTime.of(15, 0),
                LocalTime.of(11, 0),
                LocalTime.of(16,0),
                LocalTime.of(18, 0),
                LocalTime.of(23, 0),
                LocalTime.of(18, 0),
                LocalTime.of(0, 0),
                "(21) 99090-9090",
                new Address(
                        "22640102",
                        "Avenida das Américas",
                        "Barra da Tijuca",
                        "Rio de Janeiro",
                        "RJ",
                        "21",
                        4666
                )
                );
        service.save(restaurant);
    }
}
