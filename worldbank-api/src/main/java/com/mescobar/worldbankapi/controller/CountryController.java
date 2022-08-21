package com.mescobar.worldbankapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mescobar.worldbankapi.model.Country;
import com.mescobar.worldbankapi.model.Pagina;
import com.mescobar.worldbankapi.service.CountryService;

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
@RequestMapping("/v1/api/countries")
@Tag(name = "Country Controller", description = "Api para requisicao de paises")
@CrossOrigin(origins = "http://localhost:4200")
public class CountryController {

  private final CountryService countryService;

  @Operation(summary = "obter a lista de paises paginada")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "requisicao realizada com sucesso",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Country.class))}),
      @ApiResponse(responseCode = "400", description = "requisicao invalida", content = @Content)})
  @GetMapping
  public Pagina<Country> getCountries(
      @Parameter(description="número da pagina, valor padrão:1")
      @RequestParam(required = false, defaultValue = "1") Integer pageNum,
      @Parameter(description="Quantidade de itens por pagina, valor padrão:10")
      @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

    return countryService.getCountries(pageNum, pageSize);
  }

}
