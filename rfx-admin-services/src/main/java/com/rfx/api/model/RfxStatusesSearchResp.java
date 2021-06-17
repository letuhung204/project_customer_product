package com.rfx.api.model;

import org.springframework.data.domain.Page;

public class RfxStatusesSearchResp {
	private Page<TableRfxStatuses> rfxStatusesSearch;
	private String message;
	private int code;
    public Page<TableRfxStatuses> getRfxStatusesSearch() {
		return rfxStatusesSearch;
	}
	public void setRfxStatusesSearch(Page<TableRfxStatuses> rfxStatusesSearch) {
		this.rfxStatusesSearch = rfxStatusesSearch;
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
