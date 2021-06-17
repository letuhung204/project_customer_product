package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDetailsSearch {

	private int page_no;
	private int limit;
    private String sortby; // Ascending / Descending
    private String orderby; 
    
	private String name;
	private String websiteUrl;
	private String contact;
	private String legalStruct;
	private String background;
	private String history;
	private String revenueGrowth;
	private String keyStaffProfile;
	private String headQuaterAddress;
	private String capabilityReferences;
	private String companyClients;
	private String status;
}
