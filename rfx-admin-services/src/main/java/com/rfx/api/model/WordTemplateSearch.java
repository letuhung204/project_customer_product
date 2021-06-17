package com.rfx.api.model;



import lombok.Data;

@Data
public class WordTemplateSearch {
	private int page_no;
    private int limit;
    private String sortby; // Ascending / Descending
    private String orderby;
    private String templateName;
    private String description;
    private String status;
    
    
}
