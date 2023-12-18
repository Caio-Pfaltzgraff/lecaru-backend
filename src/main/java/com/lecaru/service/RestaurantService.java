package com.lecaru.service;

import com.lecaru.domain.model.restaurant.Restaurant;
import com.lecaru.domain.repository.RestaurantRepository;
import com.lecaru.infra.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService implements CrudService<Restaurant, UUID> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(UUID id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException(Restaurant.class));
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(UUID id, Restaurant restaurant) {
        restaurant.setId(id);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(UUID id) {
        var restaurant = findById(id);
        restaurantRepository.delete(restaurant);
    }
}
