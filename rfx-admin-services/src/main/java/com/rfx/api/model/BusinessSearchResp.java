
package com.rfx.api.model;

import org.springframework.data.domain.Page;


public class BusinessSearchResp {
    private Page<TableBusinessUnit> businessSearch;
    private String message;
    private int code;

    public Page<TableBusinessUnit> getBusinessSearch() {
        return businessSearch;
    }
    public void setBusinessSearch(Page<TableBusinessUnit> businessSearch) {
        this.businessSearch = businessSearch;
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
