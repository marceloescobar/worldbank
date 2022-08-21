package com.mescobar.worldbankapi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mescobar.worldbankapi.model.Indicator;
import com.mescobar.worldbankapi.model.Pagina;
import com.mescobar.worldbankapi.service.IndicatorService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndicatorServiceDefaultImpl extends BaseRestService implements IndicatorService {

  @Value("${url.indicador}")
  private String urlIndicador;

  @Override
  public Pagina<Indicator> getIndicatorsByCountry(String countryID, Integer pageNum,
      Integer pageSize) {

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("country_code", countryID);
    parameters.put("per_page", pageSize);
    parameters.put("page", pageNum);

    ResponseEntity<List<?>> responseEntity = this.consumirURL(urlIndicador, HttpMethod.GET,
        new ParameterizedTypeReference<List<?>>() {}, parameters);

    Pagina<Indicator> pagina = this.parserResponse(responseEntity, Indicator.class);

    return pagina;
  }

}
