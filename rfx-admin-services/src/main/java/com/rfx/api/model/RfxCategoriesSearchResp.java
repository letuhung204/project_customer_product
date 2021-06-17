package com.rfx.api.model;

import org.springframework.data.domain.Page;

public class RfxCategoriesSearchResp {
	private Page<TableRfxCategories> rfxCategoriesSearch;
    private String message;
    private int code;
	public Page<TableRfxCategories> getRfxCategoriesSearch() {
		return rfxCategoriesSearch;
	}
	public void setRfxCategoriesSearch(Page<TableRfxCategories> rfxCategoriesSearch) {
		this.rfxCategoriesSearch = rfxCategoriesSearch;
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
