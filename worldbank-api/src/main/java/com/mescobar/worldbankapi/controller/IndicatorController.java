package com.mescobar.worldbankapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mescobar.worldbankapi.model.Indicator;
import com.mescobar.worldbankapi.model.Pagina;
import com.mescobar.worldbankapi.service.IndicatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/indicators")
@Tag(name = "Indicator Controller", description = "Api para requisicao de indicadores")
@CrossOrigin(origins = "http://localhost:4200")
public class IndicatorController {

  private final IndicatorService indicatorService;
  
  @Operation(summary = "obter indicadores por pais")
  @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "lista de indicadores por pais", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Indicator.class)) }),
      @ApiResponse(responseCode = "400", description = "Id de pais inválido", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "indicador nao encontrado", 
        content = @Content) })
  
  @GetMapping("/{countryId}")
  public Pagina<Indicator> getIndicatorByCountry(
      @Parameter(description = "id do pais para obter os indicadores", required = true) 
      @PathVariable String countryId,
      @RequestParam(required = false, defaultValue = "1") Integer pageNum,
      @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
    
    return indicatorService.getIndicatorsByCountry(countryId, pageNum, pageSize);
    
  }

}
