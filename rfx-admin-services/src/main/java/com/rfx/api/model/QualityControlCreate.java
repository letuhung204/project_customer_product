package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualityControlCreate {

	private int qualityControlId;
	private String businessUnitId; 
	private String businessUnitTypeId;
	private String qualityControlName;  
	private String qualityControlDetails;  
	private String certifications; 

}
