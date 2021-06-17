package com.rfx.api.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class CompanyServiceSearchResp {
    private Page<TableCompanyServices> companyServices;
    private String message;
    private int code;
}
