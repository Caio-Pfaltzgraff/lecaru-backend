package com.lecaru.domain.model.restaurant.dto;

import java.time.LocalTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestaurantCreateDTO(
        @NotBlank
        String title,
        @NotNull
        LocalTime lunchOpenWeekdays,
        @NotNull
        LocalTime lunchCloseWeekdays,
        @NotNull
        LocalTime lunchOpenWeekends,
        @NotNull
        LocalTime lunchCloseWeekends,
        @NotNull
        LocalTime dinnerOpenWeekdays,
        @NotNull
        LocalTime dinnerCloseWeekdays,
        @NotNull
        LocalTime dinnerOpenWeekends,
        @NotNull
        LocalTime dinnerCloseWeekends,
        @NotBlank
        String telephone,
        @NotNull @Valid
        AddressCreateDTO address
) {
}
