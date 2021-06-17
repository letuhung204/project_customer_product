package com.rfx.api.model;

import org.springframework.data.domain.Page;

public class ProposalDocumentTypesSearchResp {
	private Page<TableProposalDocumentTypes> proposalDocumentTypes;
    private String message;
    private int code;

	public Page<TableProposalDocumentTypes> getProposalDocumentTypes() {
		return proposalDocumentTypes;
	}
	public void setProposalDocumentTypes(Page<TableProposalDocumentTypes> proposalDocumentTypes) {
		this.proposalDocumentTypes = proposalDocumentTypes;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
