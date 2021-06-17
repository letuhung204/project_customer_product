package com.rfx.api.model;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentEntryCreate {


	private String document_entry_id;
	private String rfx_document_type_id;
	private String document_title;
	private String rfx_type_id;
	private String rfx_category_id;
	private String client_name;
	private File file;
	private String created_at;
	private String updated_at;
	public String getDocument_entry_id() {
		return document_entry_id;
	}
	public void setDocument_entry_id(String document_entry_id) {
		this.document_entry_id = document_entry_id;
	}
	public String getRfx_document_type_id() {
		return rfx_document_type_id;
	}
	public void setRfx_document_type_id(String rfx_document_type_id) {
		this.rfx_document_type_id = rfx_document_type_id;
	}
	public String getDocument_title() {
		return document_title;
	}
	public void setDocument_title(String document_title) {
		this.document_title = document_title;
	}
	public String getRfx_type_id() {
		return rfx_type_id;
	}
	public void setRfx_type_id(String rfx_type_id) {
		this.rfx_type_id = rfx_type_id;
	}
	public String getRfx_category_id() {
		return rfx_category_id;
	}
	public void setRfx_category_id(String rfx_category_id) {
		this.rfx_category_id = rfx_category_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
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
