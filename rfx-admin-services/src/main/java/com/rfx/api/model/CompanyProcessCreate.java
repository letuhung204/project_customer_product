package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyProcessCreate {
private int processId;
private String businessUnitId;
private String businessUnitTypeId;
private String processName;
private String processFunction; 
private String processDetails;  
private String nigpUnpscCodes;  
private String status; 
private String createdAt; 
private String updatedAt; 
}
