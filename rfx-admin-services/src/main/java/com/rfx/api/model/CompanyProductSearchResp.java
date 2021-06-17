package com.rfx.api.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class CompanyProductSearchResp {
	    private Page<TableCompanyProducts> companyProducts;
	    private String message;
	    private int code;

}
