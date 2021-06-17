package com.rfx.api.model;

import lombok.Data;

@Data
public class CompanyProcessSearch {
	private int page_no;
	private int limit;
    private String sortby; // Ascending / Descending
    private String orderby; 
	private String businessUnitId; // private String businessUnitId;
    private String businessUnitTypeId;
	private String processName;
	private String processFunction; 
	private String processDetails;  
	private String nigpUnpscCodes;  
	private String status; 
    
}
