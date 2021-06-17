package com.rfx.api.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class CompanyProcessSearchResp {
	private Page<TableCompanyProcess> companyProcess;
    private String message;
    private int code;
}
