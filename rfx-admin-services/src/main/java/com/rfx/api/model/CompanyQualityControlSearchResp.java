package com.rfx.api.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class CompanyQualityControlSearchResp {
	    private Page<TableCompanyQualityControl> companyQualityControl;
	    private String message;
	    private int code;
	
}
