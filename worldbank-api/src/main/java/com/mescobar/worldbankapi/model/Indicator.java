package com.mescobar.worldbankapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Indicator {
  private IndicatorData indicator;
  private String countryiso3code;
  private String value;
  private String date;
  private String unit;
  private String obs_status;
  private int decimal;
}
