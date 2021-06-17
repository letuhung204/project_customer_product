package com.rfx.api.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class CompanySecuritySearchResp {
	    private Page<TableCompanySecurity> companySecurity;
	    private String message;
	    private int code;
}
