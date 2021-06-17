package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentEntriesCreate {


	private String content_entry_id;
	private String name;
	private String keyword;
	private String topic;
	private String content;
	private String effectivedate;
	private String rfx_category_id;
	private String created_at;
	private String updated_at;
	
	public String getContent_entry_id() {
		return content_entry_id;
	}
	public void setContent_entry_id(String content_entry_id) {
		this.content_entry_id = content_entry_id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEffectivedate() {
		return effectivedate;
	}
	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}
	public String getRfx_category_id() {
		return rfx_category_id;
	}
	public void setRfx_category_id(String rfx_category_id) {
		this.rfx_category_id = rfx_category_id;
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
}
