package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class DocumentEntrySearch {
	private int page_no;
	private int limit;
    private String sortby; // Ascending / Descending
    private String orderby; 
	private String rfx_document_type_id;
	private String title;
	private String clientname;
	private String status;
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


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	

}
