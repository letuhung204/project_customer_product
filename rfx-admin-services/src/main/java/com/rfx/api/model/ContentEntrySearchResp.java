package com.rfx.api.model;

import org.springframework.data.domain.Page;

public class ContentEntrySearchResp {
	private Page<TableContentEntries> contentEntries;
	private String message;
    private int code;
    public Page<TableContentEntries> getContentEntries() {
		return contentEntries;
	}
	public void setContentEntries(Page<TableContentEntries> contentEntries) {
		this.contentEntries = contentEntries;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

}
