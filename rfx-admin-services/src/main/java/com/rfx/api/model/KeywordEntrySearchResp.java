package com.rfx.api.model;

import org.springframework.data.domain.Page;

public class KeywordEntrySearchResp {
	private Page<TableKeywordEntries> keywordEntries;
    private String message;
    private int code;
	public Page<TableKeywordEntries> getKeywordEntries() {
		return keywordEntries;
	}
	public void setKeywordEntries(Page<TableKeywordEntries> keywordEntries) {
		this.keywordEntries = keywordEntries;
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
