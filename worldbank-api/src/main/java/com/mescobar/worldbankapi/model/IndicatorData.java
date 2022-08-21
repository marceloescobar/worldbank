package com.mescobar.worldbankapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndicatorData {
  private String id;
  private String value;
}
