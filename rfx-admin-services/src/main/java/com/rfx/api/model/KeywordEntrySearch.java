package com.rfx.api.model;

public class KeywordEntrySearch {

	private int page_no;
    private int limit;
    private String sortby; // Ascending / Descending
    private String orderby; 
	private String bid_source;
	private String rfx_category_id;
	private String exclusions;
	private String status;
	private String business_id; 
	private String effectivedate_st;
    private String effective_end;
    
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

	public String getBidSource() {
		return bid_source;
	}
	public void setBidSource(String bid_source) {
		this.bid_source = bid_source;
	}
	public String getRfxCategoryId() {
		return rfx_category_id;
	}
	public void setRfxCategoryId(String rfx_category_id) {
		this.rfx_category_id = rfx_category_id;
	}
	public String getExclusions() {
		return exclusions;
	}
	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}
	public String getBusinessId() {
		return business_id;
	}
	public void setBusinessId(String business_id) {
		this.business_id = business_id;
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
