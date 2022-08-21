package com.mescobar.worldbankapi.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.mescobar.worldbankapi.model.Country;
import com.mescobar.worldbankapi.model.Pagina;


@SpringBootTest
public class CountryServiceTest {

  @MockBean
  private RestTemplate restTemplate;

  @Autowired
  private CountryService countryService;

  @BeforeEach
  public void setUp() throws Exception {
    Map<String, Object> pageHeaders = new HashMap<String, Object>();
    pageHeaders.put("page", 1);
    pageHeaders.put("pages", 10);
    pageHeaders.put("per_page", 50);
    pageHeaders.put("total", 300);

    List<Object> registros = new ArrayList<Object>();
    
    List<Object> listInterna = new ArrayList<Object>();
    listInterna.add(pageHeaders);
    listInterna.add(registros);
    
    ResponseEntity<List<Object>> responseEntity =
        new ResponseEntity<List<Object>>((List<Object>) listInterna, HttpStatus.OK);

    Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class),
        Mockito.isNull(), Mockito.any(ParameterizedTypeReference.class),
        Mockito.anyMap())).thenReturn(responseEntity);

  }

  @Test
  public void testConsumirServicoCountries() {
    Pagina<Country> cPagina = countryService.getCountries(1, 10);
    assertNotNull(cPagina);
  }
}
