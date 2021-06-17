package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="document_entries")
@Table(name = "document_entries")
public class TableDocumentEntries {
	@Id
    @GeneratedValue
	private int id ;
	@Column
	private String rfx_document_type_id;
	@Column
	private String document_entry_id;
	@Column
	private String title;
	@Column
	private String rfx_type_id;
	@Column
	private String rfx_category_id;
	@Column
	private String clientname;
	@Column
	private String attachment_url;
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
	public String getDocumentEntryId() {
		return document_entry_id;
	}
	public void setDocumentEntryId(String document_entry_id) {
		this.document_entry_id = document_entry_id;
	}
	
	public String getRfxDocumentTypeId() {
		return rfx_document_type_id;
	}
	public void setRfxDocumentTypeId(String rfx_document_type_id) {
		this.rfx_document_type_id = rfx_document_type_id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getRfxTypeId() {
		return rfx_type_id;
	}
	public void setRfxTypeId(String rfx_type_id) {
		this.rfx_type_id = rfx_type_id;
	}
	
	public String getRfxCategoryId() {
		return rfx_category_id;
	}
	public void setRfxCategoryId(String rfx_category_id) {
		this.rfx_category_id = rfx_category_id;
	}

	public String getAttachmentUrl() {
		return attachment_url;
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
	public void setAttachmentUrl(String attachment_url) {
		this.attachment_url = attachment_url;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
