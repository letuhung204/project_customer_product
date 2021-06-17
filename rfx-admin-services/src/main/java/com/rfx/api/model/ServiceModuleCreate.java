package com.rfx.api.model;

import lombok.Data;

@Data
public class ServiceModuleCreate {
	
	private int serviceModuleId;
	private String serviceId; 
	private Boolean isNewField; 
	private String serviceFieldType;
	private String fieldName;
	private String fieldDescription;
	private String fieldData;
	
}
