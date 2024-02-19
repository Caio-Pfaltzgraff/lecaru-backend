package com.lecaru.domain.model.restaurant;

import java.time.LocalTime;
import java.util.UUID;

import com.lecaru.domain.model.restaurant.dto.AddressDTO;
import com.lecaru.domain.model.restaurant.dto.RestaurantCreateDTO;
import com.lecaru.domain.model.restaurant.dto.RestaurantDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id @GeneratedValue(generator = "UUID")
    private UUID id;
    private String title;

    private LocalTime lunchOpenWeekdays;
    private LocalTime lunchCloseWeekdays;
    private LocalTime lunchOpenWeekends;
    private LocalTime lunchCloseWeekends;
    private LocalTime dinnerOpenWeekdays;
    private LocalTime dinnerCloseWeekdays;
    private LocalTime dinnerOpenWeekends;
    private LocalTime dinnerCloseWeekends;

    @Column(unique = true)
    private String telephone;
    @Embedded
    private Address address;

    public Restaurant(RestaurantCreateDTO restaurant) {
        this.title = restaurant.title();
        this.lunchOpenWeekdays = restaurant.lunchOpenWeekdays();
        this.lunchCloseWeekdays = restaurant.lunchCloseWeekdays();
        this.lunchOpenWeekends = restaurant.lunchOpenWeekends();
        this.lunchCloseWeekends = restaurant.lunchCloseWeekends();
        this.dinnerOpenWeekdays = restaurant.dinnerOpenWeekdays();
        this.dinnerCloseWeekdays = restaurant.dinnerCloseWeekdays();
        this.dinnerOpenWeekends = restaurant.dinnerOpenWeekends();
        this.dinnerCloseWeekends = restaurant.dinnerCloseWeekends();
        this.telephone = restaurant.telephone();
        this.address = new Address(restaurant.address());
    }

    public RestaurantDTO toDTO() {
        return new RestaurantDTO(
            id, title, 
            lunchOpenWeekdays, lunchCloseWeekdays, lunchOpenWeekends, lunchCloseWeekends, 
            dinnerOpenWeekdays, dinnerCloseWeekdays, dinnerOpenWeekends, dinnerCloseWeekends, 
            new AddressDTO(
                address.getCep(), address.getLogradouro(), address.getBairro(), 
                address.getLocalidade(), address.getUf(),address.getDdd(), address.getNumber()
            )
        );
    }

    public void update(Restaurant restaurant) {

        if (restaurant.title != null) {
            this.title = restaurant.title;
        }
        if (restaurant.lunchOpenWeekdays != null) {
            this.lunchOpenWeekdays = restaurant.lunchOpenWeekdays;
        }
        if (restaurant.lunchCloseWeekdays != null) {
            this.lunchCloseWeekdays = restaurant.lunchCloseWeekdays;
        }
        if (restaurant.lunchOpenWeekends != null) {
            this.lunchOpenWeekends = restaurant.lunchOpenWeekends;
        }
        if (restaurant.lunchCloseWeekends != null) {
            this.lunchCloseWeekends = restaurant.lunchCloseWeekends;
        }
        if (restaurant.dinnerOpenWeekdays != null) {
            this.dinnerOpenWeekdays = restaurant.dinnerOpenWeekdays;
        }
        if (restaurant.dinnerCloseWeekdays != null) {
            this.dinnerCloseWeekdays = restaurant.dinnerCloseWeekdays;
        }
        if (restaurant.dinnerOpenWeekends != null) {
            this.dinnerOpenWeekends = restaurant.dinnerOpenWeekends;
        }
        if (restaurant.dinnerCloseWeekends != null) {
            this.dinnerCloseWeekends = restaurant.dinnerCloseWeekends;
        }
        if (restaurant.telephone != null) {
            this.telephone = restaurant.telephone;
        }
        if (restaurant.address != null) {
            address.update(address);
        }
    }
}
