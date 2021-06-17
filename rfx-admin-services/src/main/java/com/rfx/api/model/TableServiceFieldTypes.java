package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="service_field_types")
@Table(name = "service_field_types")
public class TableServiceFieldTypes {
	@Id
    @GeneratedValue
	@Column(name="id")
	private int id;
    
	@Column(name="field_type")
	private String serviceFieldType;
	
	@Column(name="field_description")
	private String fieldDescription;

	@Column(name="status")
	private String status;
	
	@Column(name="created_at")
	private String createdAt;
	
	@Column(name="updated_at")
	private String updatedAt;

}
