

package com.rfx.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessUnitCreate {
    private String business_id;
    private String name;
	private Boolean isNewType;
    private String type; 
	private String description;
    private String code;
    private String effectivedate;

    public String getBusiness_id() {
        return business_id;
    }
    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
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

    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Boolean getIsNewType() {
		return isNewType;
	}
	public void setIsNewType(Boolean isNewType) {
		this.isNewType = isNewType;
	}
}
