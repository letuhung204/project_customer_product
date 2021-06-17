

package com.rfx.api.model;

import javax.persistence.*;

/**
 * Created by hubinotech on 06/04/20.
 */
@Entity(name="business_units")
@Table(name = "business_units")
public class TableBusinessUnit {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String business_id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String type;
	@Column
    private String code;
    @Column
    private String status;
    @Column
    private String effectivedate;
    @Column
    private String updated_at;
    @Column
    private String created_at;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getEffectivedate() {
        return effectivedate;
    }
    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate;
    }
	public String getBusinessId() {
		return business_id;
	}
	public void setBusinessId(String business_id) {
		this.business_id = business_id;
	}
	public String getUpdatedAt() {
		return updated_at;
	}
	public void setUpdatedAt(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}


}
