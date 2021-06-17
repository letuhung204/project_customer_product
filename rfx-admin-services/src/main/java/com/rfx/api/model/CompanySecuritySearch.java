package com.rfx.api.model;

import lombok.Data;

@Data
public class CompanySecuritySearch {
	private int page_no;
	private int limit;
    private String sortby; // Ascending / Descending
    private String orderby; 
    
    private String businessUnitId;
    private String businessUnitTypeId;
	private String securityName;  
	private String securityDetails;  
	private String certifications; 
	private String status; 
}
