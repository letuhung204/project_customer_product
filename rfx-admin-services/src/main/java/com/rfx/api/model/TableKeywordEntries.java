package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="keyword_entries")
@Table(name = "keyword_entries")
public class TableKeywordEntries {
	@Id
    @GeneratedValue
    private int id;
    @Column
	private String keyword_entry_id;
    @Column
    private String bid_source;
    @Column
    private String rfx_category_id;
	@Column
    private String exclusions;
    @Column
    private String business_id; 
    @Column
    private String effectivedate; 

    @Column
    private String status;
	@Column
    private String created_at;
    @Column
    private String updated_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeywordEntryId() {
		return keyword_entry_id;
	}
	public void setKeywordEntryId(String keyword_entry_id) {
		this.keyword_entry_id = keyword_entry_id;
	}
	public String getBidSource() {
		return bid_source;
	}
	public void setBidSource(String bid_source) {
		this.bid_source = bid_source;
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
	public String getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdatedAt() {
		return updated_at;
	}
	public void setUpdatedAt(String updated_at) {
		this.updated_at = updated_at;
	} 
    public String getEffectivedate() {
		return effectivedate;
	}
	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}

    public String getRfxCategoryId() {
		return rfx_category_id;
	}
	public void setRfxCategoryId(String rfx_category_id) {
		this.rfx_category_id = rfx_category_id;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
