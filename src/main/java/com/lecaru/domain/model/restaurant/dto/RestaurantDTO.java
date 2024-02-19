package com.lecaru.domain.model.restaurant.dto;

import java.time.LocalTime;
import java.util.UUID;

public record RestaurantDTO(
  UUID id,
  String title,
  LocalTime lunchOpenWeekdays,
  LocalTime lunchCloseWeekdays,
  LocalTime lunchOpenWeekends,
  LocalTime lunchCloseWeekends,
  LocalTime dinnerOpenWeekdays,
  LocalTime dinnerCloseWeekdays,
  LocalTime dinnerOpenWeekends,
  LocalTime dinnerCloseWeekends,
  AddressDTO address
) {
}
