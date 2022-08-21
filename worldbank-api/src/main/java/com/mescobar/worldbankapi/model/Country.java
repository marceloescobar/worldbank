package com.mescobar.worldbankapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
  
  @Schema(description = "identificador de pais", 
      example = "BR", required = true)
	private String id;
	private String name;

}
