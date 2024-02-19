package com.lecaru.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lecaru.domain.model.restaurant.Restaurant;
import com.lecaru.domain.model.restaurant.dto.RestaurantCreateDTO;
import com.lecaru.domain.repository.RestaurantRepository;
import com.lecaru.infra.exception.NotFoundException;

@Service
public class RestaurantService implements CrudService<Restaurant, RestaurantCreateDTO, UUID> {

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
    @CacheEvict(value = "restaurant", allEntries = true)
    public Restaurant save(RestaurantCreateDTO restaurant) {
        return restaurantRepository.save(new Restaurant(restaurant));
    }
    
    @Transactional
    @CacheEvict(value = "restaurant", allEntries = true)
    public Restaurant update(UUID id, RestaurantCreateDTO restaurantDTO) {
        var restaurant = findById(id);
        restaurant.update(new Restaurant(restaurantDTO));
        return restaurant;
    }
    
    @Transactional
    @CacheEvict(value = "restaurant", allEntries = true)
    public void delete(UUID id) {
        var restaurant = findById(id);
        restaurantRepository.delete(restaurant);
    }
}
