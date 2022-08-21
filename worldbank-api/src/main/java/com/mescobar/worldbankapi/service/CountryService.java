package com.mescobar.worldbankapi.service;

import com.mescobar.worldbankapi.model.Country;
import com.mescobar.worldbankapi.model.Pagina;

/**
 * @author mescobar
 * interface de servico para Country
 *
 */
public interface CountryService {

	/**
	 * obtem uma lista de paises paginada
	 * 
	 * @param pageNum obtem a pagina informada
	 * @param pageSize
	 * @return
	 */
	Pagina<Country> getCountries(Integer pageNum, Integer pageSize);
}
