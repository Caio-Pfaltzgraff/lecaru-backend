package com.lecaru.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lecaru.domain.repository.ProductRepository;
import com.lecaru.domain.repository.RestaurantRepository;
import com.lecaru.utils.HomeData;
import com.lecaru.utils.Suggestions;
import com.lecaru.utils.Units;

@Service
public class HomeService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Transactional(readOnly = true)
  public HomeData findRestaurantSuggestionsAndUnits() {
    //buscar todos os produtos e restaurantes
    var listProducts = productRepository.findAll();
    var listRestaurant = restaurantRepository.findAll();

    //embaralhar todos os produtos e restaurantes
    Collections.shuffle(listProducts);
    Collections.shuffle(listRestaurant);

    //busca a quantidade de cada aleatóriamente
    var listSuggestions = listProducts.subList(0, 4)
    .stream().map(
      p -> new Suggestions(p.getId(), p.getTitle(), p.getPhoto(), p.getDescription(), p.getPrice())
    ).toList();

    var listUnits = listRestaurant.subList(0, 3)
    .stream().map(
      r -> new Units(r.getId(), r.getTitle(), r.getAddress().toCompleteAddressString())
    ).toList();
    
    return new HomeData(listSuggestions, listUnits);
  }

}