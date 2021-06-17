package com.rfx.api.model;

import org.springframework.data.domain.Page;

public class DocumentEntrySerachResp {
	
	private Page<TableDocumentEntries> documentEntries;
	private String message;
    private int code;
	
	public Page<TableDocumentEntries> getDocumentEntries() {
		return documentEntries;
	}
	public void setDocumentEntries(Page<TableDocumentEntries> documentEntries) {
		this.documentEntries = documentEntries;
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
