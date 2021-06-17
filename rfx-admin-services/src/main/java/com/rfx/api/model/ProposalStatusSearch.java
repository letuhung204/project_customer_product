package com.rfx.api.model;

import lombok.Data;

@Data
public class ProposalStatusSearch {
	private int page_no;
    private int limit;
    private String sortby; // Ascending / Descending
    private String orderby; // Code / Name
    private String name;
    private String code;
    private String status;
    private String effectivedate_st;
    private String effective_end;

}
