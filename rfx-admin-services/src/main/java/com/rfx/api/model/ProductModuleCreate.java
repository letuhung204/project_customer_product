package com.rfx.api.model;

import lombok.Data;

@Data
public class ProductModuleCreate {
	
	private int productModuleId;
	private String productId; 
	private Boolean isNewField; 
	private String productFieldType;
	private String fieldName;
	private String fieldDescription;
	private String fieldData;
	
}
