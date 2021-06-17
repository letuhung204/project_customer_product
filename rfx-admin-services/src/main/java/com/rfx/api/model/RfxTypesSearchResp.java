package com.rfx.api.model;

import org.springframework.data.domain.Page;

public class RfxTypesSearchResp {
	private Page<TableRfxTypes> rfxTypesSearch;
    private String message;
    private int code;
    public Page<TableRfxTypes> getRfxTypesSearch() {
		return rfxTypesSearch;
	}
	public void setRfxTypesSearch(Page<TableRfxTypes> rfxTypesSearch) {
		this.rfxTypesSearch = rfxTypesSearch;
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
