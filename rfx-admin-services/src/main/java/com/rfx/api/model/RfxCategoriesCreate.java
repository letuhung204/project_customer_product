package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RfxCategoriesCreate {
	private String rfx_category_id;
	private String name;
    private String description;
    private String code;
    private String effectivedate;
    
 
	public String getRfx_category_id() {
		return rfx_category_id;
	}
	public void setRfx_category_id(String rfx_category_id) {
		this.rfx_category_id = rfx_category_id;
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
