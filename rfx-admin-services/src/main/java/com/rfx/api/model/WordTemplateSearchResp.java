package com.rfx.api.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class WordTemplateSearchResp {
	private Page<TableWordTemplateAttachments> wordTemplateAttachments;
    private String message;
    private int code;
}
