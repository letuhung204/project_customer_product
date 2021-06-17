package com.rfx.api.model;

import lombok.Data;

@Data
public class CompanyMethodologySearch {
	private int page_no;
	private int limit;
    private String sortby; // Ascending / Descending
    private String orderby; 
    
	private String businessUnitId; 
    private String businessUnitTypeId;
	private String methodologyName;  
	private String methodologyDetails;  
	private String certifications; 
	private String status; 

}
