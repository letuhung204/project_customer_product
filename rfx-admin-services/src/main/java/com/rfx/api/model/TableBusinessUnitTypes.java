package com.rfx.api.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="business_unit_types")
@Table(name = "business_unit_types")
public class TableBusinessUnitTypes {
	@Id
    @GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="type_name")
	private String typeName; 
	@Column(name="status")
	private String status; 
	@Column(name="created_at")
	private String createdAt; 
	@Column(name="updated_at")
	private String updatedAt;
}
