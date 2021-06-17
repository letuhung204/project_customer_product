package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeywordEntryCreate {
	private String keyword_entry_id;
	private String bid_source;
	private String rfx_category_id;
	private String exclusions;
	private String business_id; 
	private String effectivedate;
	private String created_at;
	private String updated_at; 
	public String getKeyword_entry_id() {
		return keyword_entry_id;
	}
	public void setKeyword_entry_id(String keyword_entry_id) {
		this.keyword_entry_id = keyword_entry_id;
	}
	public String getBid_source() {
		return bid_source;
	}
	public void setBid_source(String bid_source) {
		this.bid_source = bid_source;
	}
	public String getExclusions() {
		return exclusions;
	}
	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getEffectivedate() {
		return effectivedate;
	}
	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}
	
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getRfx_category_id() {
		return rfx_category_id;
	}
	public void setRfx_category_id(String rfx_category_id) {
		this.rfx_category_id = rfx_category_id;
	}


}
