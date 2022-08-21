package com.mescobar.worldbankapi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mescobar.worldbankapi.model.Country;
import com.mescobar.worldbankapi.model.Pagina;
import com.mescobar.worldbankapi.service.CountryService;
import lombok.RequiredArgsConstructor;

/**
 * @author mescobar
 * 
 *         implementacao default do servico countryservice utlizando o consumo via API REST
 *
 */
@Service
@RequiredArgsConstructor
public class CountryServiceDefaultImpl extends BaseRestService implements CountryService {

  @Value("${url.paises}")
  private String urlPaises;

  @Override
  public Pagina<Country> getCountries(Integer pageNum, Integer pageSize) {

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("per_page", pageSize);
    parameters.put("page", pageNum);

    ResponseEntity<List<?>> responseEntity = this.consumirURL(urlPaises, HttpMethod.GET,
        new ParameterizedTypeReference<List<?>>() {}, parameters);

    return this.parserResponse(responseEntity, Country.class);
  }


}
