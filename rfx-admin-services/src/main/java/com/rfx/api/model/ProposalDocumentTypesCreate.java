package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalDocumentTypesCreate {
	private String proposal_document_type_id;
	private String name;
    private String description;
    private String code;
    private String effectivedate;
	public String getProposal_document_type_id() {
		return proposal_document_type_id;
	}
	public void setProposal_document_type_id(String proposal_document_type_id) {
		this.proposal_document_type_id = proposal_document_type_id;
	}
 
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getEffectivedate() {
        return effectivedate;
    }
    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate;
    }
	
	
}
