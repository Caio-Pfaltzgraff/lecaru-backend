package com.lecaru.service;

import com.lecaru.domain.model.restaurant.Restaurant;
import com.lecaru.domain.repository.RestaurantRepository;
import com.lecaru.infra.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService implements CrudService<Restaurant, UUID> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Restaurant findById(UUID id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException(Restaurant.class));
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant update(UUID id, Restaurant restaurant) {
        var restaurantToUpdate = findById(id);
        restaurantToUpdate.update(restaurant);
        return restaurantToUpdate;
    }

    @Transactional
    public void delete(UUID id) {
        var restaurant = findById(id);
        restaurantRepository.delete(restaurant);
    }
}
