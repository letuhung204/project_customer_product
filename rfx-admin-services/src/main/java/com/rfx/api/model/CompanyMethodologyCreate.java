package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyMethodologyCreate {
	
	private int methodologyId;
	private String businessUnitId; 
	private String businessUnitTypeId;
	private String methodologyName;  
	private String methodologyDetails;  
	private String certifications; 


	
}
