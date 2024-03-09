package com.lecaru.utils;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Units {
  private UUID id;
  private String title;
  private String address;
}
