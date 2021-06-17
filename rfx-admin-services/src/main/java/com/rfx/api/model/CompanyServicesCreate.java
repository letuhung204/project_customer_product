package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyServicesCreate {

	private int serviceId;
	private String businessUnitId;
	private String businessUnitTypeId;
	private String serviceName;
	private String serviceCategory;
	private String serviceDescription;
	private String nigpUnpscCodes;

}
