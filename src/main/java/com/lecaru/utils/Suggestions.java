package com.lecaru.utils;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suggestions {
  private UUID id;
  private String title;
  private String urlImage;
  private String description;
  private BigDecimal pricing;
}
