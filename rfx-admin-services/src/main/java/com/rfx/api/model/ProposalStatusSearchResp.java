package com.rfx.api.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class ProposalStatusSearchResp {
	private Page<TableProposalStatus> proposalStatus;
    private String message;
    private int code;

}
