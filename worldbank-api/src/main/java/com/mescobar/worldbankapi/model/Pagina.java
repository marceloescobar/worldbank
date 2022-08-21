package com.mescobar.worldbankapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagina<T> {
	private int page;
	private int pages;
	private int per_page;
	private int total;
	List<T> content;
}
