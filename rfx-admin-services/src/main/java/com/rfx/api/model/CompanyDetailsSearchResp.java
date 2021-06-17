package com.rfx.api.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class CompanyDetailsSearchResp {
	private Page<TableCompanyDetails> companyDetails;
	private String message;
    private int code;

}
