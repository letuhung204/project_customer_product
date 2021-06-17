package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDetailsCreate {
	
	private int companyDetailsId;
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

	//description fields
	private String nameDesc;
	private String websiteUrlDesc;
	private String contactDesc;
	private String legalStructDesc;
	private String backgroundDesc;
	private String historyDesc;
	private String revenueGrowthDesc;
	private String keyStaffProfileDesc;
	private String headQuaterAddressDesc;
	private String capabilityReferencesDesc;
	private String companyClientsDesc;
	
	
	
	

}
