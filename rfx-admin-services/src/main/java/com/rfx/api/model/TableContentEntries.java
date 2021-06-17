package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="content_entries")
@Table(name = "content_entries")
public class TableContentEntries {
	
		@Id
	    @GeneratedValue
	    private int id;
	    @Column
	    private String content_entry_id;
	    @Column
	    private String name;
	    @Column
	    private String keyword;
	    @Column
	    private String topic;
	    @Column
	    private String content;
	    @Column
	    private String status;
		@Column
	    private String rfx_category_id;
	    @Column
	    private String effectivedate;
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
		public String getContentEntryId() {
			return content_entry_id;
		}
		public void setContentEntryId(String content_entry_id) {
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
		public String getRfxCategoryId() {
			return rfx_category_id;
		}
		public void setRfxCategoryId(String rfx_category_id) {
			this.rfx_category_id = rfx_category_id;
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

	    public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}

}
