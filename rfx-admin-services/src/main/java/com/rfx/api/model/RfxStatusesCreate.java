package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RfxStatusesCreate {
	
	private String rfx_status_id;
	private String name;
    private String description;
    private String code;
    private String effectivedate;
    
 

	public String getName() {
        return name;
    }
	public void setName(String name) {
        this.name = name;
    }
    public String getRfx_status_id() {
		return rfx_status_id;
	}
	public void setRfx_status_id(String rfx_status_id) {
		this.rfx_status_id = rfx_status_id;
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
