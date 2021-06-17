package com.rfx.api.model;

import lombok.Data;

@Data
public class ProposalStatusCreate {
	private String proposalStatusId;
	private String name;
    private String description;
    private String code;
    private String effectivedate;
}
