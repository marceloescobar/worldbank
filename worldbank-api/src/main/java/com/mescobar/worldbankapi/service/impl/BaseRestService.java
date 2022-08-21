package com.mescobar.worldbankapi.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mescobar.worldbankapi.exception.ConsumeException;
import com.mescobar.worldbankapi.model.Pagina;

/**
 * @author mescobar
 *
 */
public abstract class BaseRestService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ObjectMapper mapper;



  /**
   * @param <T>
   * @param url
   * @param metodo
   * @param tipoParametro
   * @param parameters
   * @return
   */
  protected <T> ResponseEntity<T> consumirURL(String url, HttpMethod metodo,
      ParameterizedTypeReference<T> tipoParametro, Map<String, Object> parameters) {
    return restTemplate.exchange(url, metodo, null, tipoParametro, parameters);
  }

  /**
   * @param <T>
   * @param obj
   * @return
   */
  protected <T> Pagina<T> converterPagina(Object obj) {
    return mapper.convertValue(obj, Pagina.class);
  }

  /**
   * @param <T>
   * @param registros
   * @param clazz
   * @return
   */
  protected <T> List<T> converterRegistros(List<?> registros, Class<T> clazz) {
    return registros.stream().map(object -> mapper.convertValue(object, clazz))
        .collect(Collectors.toList());
  }

  /**
   * @param <T>
   * @param responseEntity
   * @param clazz
   * @return
   */
  protected <T> Pagina<T> parserResponse(ResponseEntity<List<?>> responseEntity, Class<T> clazz) {
    HttpStatus statusCode = responseEntity.getStatusCode();

    if (statusCode == HttpStatus.OK) {
      Optional<List<?>> response = Optional.of(responseEntity.getBody());

      List<?> list = response
          .orElseThrow(() -> new ConsumeException("falha ao consumir api - conteudo inválido"));

      if (list.size() < 2)
        new ConsumeException("falha ao consumir api countries");

      // elemento 1 - vem com os dados de paginacao
      Pagina<T> pagina = this.converterPagina(list.get(0));

      // elemento 2 - vem com os registros
      if (list.get(1) != null)
        pagina.setContent(this.converterRegistros((List<?>) list.get(1), clazz));

      return pagina;

    } else {
      throw new ConsumeException("erro ao consumir api" + statusCode);
    }

  }
}
