package com.rfx.api.model;

import lombok.Data;

@Data
public class CompanySecurityCreate {
	private int securityId;
	private String businessUnitId; 
	private String businessUnitTypeId;
	private String securityName;  
	private String securityDetails;  
	private String certifications; 
}
