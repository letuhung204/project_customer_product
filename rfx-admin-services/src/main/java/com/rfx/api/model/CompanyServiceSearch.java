package com.rfx.api.model;

import lombok.Data;

@Data
public class CompanyServiceSearch {
	private int page_no;
	private int limit;
    private String sortby; // Ascending / Descending
    private String orderby;
    
    private String businessUnitId;
    private String businessUnitTypeId;
	private String serviceName;
	private String serviceCategory;
	private String serviceDescription;
	private String nigpUnpscCodes;
	private String status;
}
