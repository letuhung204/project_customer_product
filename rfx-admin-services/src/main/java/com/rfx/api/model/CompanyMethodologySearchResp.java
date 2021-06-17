package com.rfx.api.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class CompanyMethodologySearchResp {
	private Page<TableCompanyMethodology> companyMethodology;
    private String message;
    private int code;
}
