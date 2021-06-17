package com.rfx.api.model;

import lombok.Data;

@Data
public class CompanyProductSearch {
	private int page_no;
	private int limit;
    private String sortby; // Ascending / Descending
    private String orderby; 
    
    private String businessUnitId;
    private String businessUnitTypeId;
	private String productName;
	private String productFunction;
	private String productDetails;
	private String nigpUnpscCodes;
	private String status;
    
}
