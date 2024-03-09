package com.lecaru.utils;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeData {
  private List<Suggestions> suggestions;
  private List<Units> units;
}
