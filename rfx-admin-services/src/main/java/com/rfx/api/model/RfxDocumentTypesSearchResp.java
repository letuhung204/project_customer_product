package com.rfx.api.model;

import org.springframework.data.domain.Page;

public class RfxDocumentTypesSearchResp {
	private Page<TableRfxDocumentTypes> rfxRfxDocumentSearch;
	private String message;
    private int code;
    public Page<TableRfxDocumentTypes> getRfxDocumentSearch() {
		return rfxRfxDocumentSearch;
	}
	public void setRfxDocumentSearch(Page<TableRfxDocumentTypes> rfxRfxDocumentSearch) {
		this.rfxRfxDocumentSearch = rfxRfxDocumentSearch;
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
