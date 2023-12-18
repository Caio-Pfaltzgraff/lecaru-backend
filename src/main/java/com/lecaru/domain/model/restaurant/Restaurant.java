package com.lecaru.domain.model.restaurant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.UUID;

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

    public void update(Restaurant restaurant) {

        Field[] fields = Restaurant.class.getDeclaredFields();

        for(Field field : fields) {
            try {
                Object newValue = field.get(restaurant);
                if(newValue != null) {
                    field.set(this, newValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
