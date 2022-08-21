package com.mescobar.worldbankapi.service;

import com.mescobar.worldbankapi.model.Indicator;
import com.mescobar.worldbankapi.model.Pagina;

/**
 * 
 * @author mescobar
 *
 *classe de servico responsavel por buscar os dados de indicadores
 * 
 */
public interface IndicatorService {

  /**
   * retorna uma lista paginada  de indicadores por pais
   * @param countryID id do pais
   * @param pageNum numero da pagina solicitada
   * @param pageSize tamanho de itens por pagina
   * @return
   */
  Pagina<Indicator> getIndicatorsByCountry(String countryID, Integer pageNum, Integer pageSize);
}
