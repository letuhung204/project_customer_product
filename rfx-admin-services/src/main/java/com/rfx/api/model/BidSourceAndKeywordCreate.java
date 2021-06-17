package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BidSourceAndKeywordCreate {
	
	private String bid_source;
	private String rfxCategoryId;
	private String exclusions;
	public String getBid_source() {
		return bid_source;
	}
	public void setBid_source(String bid_source) {
		this.bid_source = bid_source;
	}
	public String getRfxCategoryId() {
		return rfxCategoryId;
	}
	public void setRfxCategoryId(String rfxCategoryId) {
		this.rfxCategoryId = rfxCategoryId;
	}
	public String getExclusions() {
		return exclusions;
	}
	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}
	
}
