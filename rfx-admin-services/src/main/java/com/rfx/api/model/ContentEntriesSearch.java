package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentEntriesSearch {
	private int page_no;
	private int limit;
    private String sortby; // Ascending / Descending
    private String orderby; 
    private String name; //Question name
    private String rfx_category_id;
	private String keyword;
    private String topic;
	private String status;
	private String effectivedate_st; //creation date
    private String effective_end;
    public String getRfxCategoryId() {
		return rfx_category_id;
	}
	public void setRfxCategoryId(String rfx_category_id) {
		this.rfx_category_id = rfx_category_id;
	}
    public int getPage_no() {
		return page_no;
	}
	public void setPage_no(int page_no) {
		this.page_no = page_no;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getSortby() {
		return sortby;
	}
	public void setSortby(String sortby) {
		this.sortby = sortby;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getEffectivedate_st() {
		return effectivedate_st;
	}
	public void setEffectivedate_st(String effectivedate_st) {
		this.effectivedate_st = effectivedate_st;
	}
	public String getEffective_end() {
		return effective_end;
	}
	public void setEffective_end(String effective_end) {
		this.effective_end = effective_end;
	}

    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
