package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyProductsCreate {
	
	private int productId;
	private String businessUnitId;
	private String businessUnitTypeId;
	private String productName;
	private String productFunction;
	private String productDetails;
	private String nigpUnpscCodes;
	
	
}
