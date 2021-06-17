package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="service_modules")
@Table(name = "service_modules")
public class TableServiceModules {
	 @Id
	    @GeneratedValue
		@Column(name="id")
		private int id;
	    
	    @Column(name="service_id")
		private String serviceId;
	    
		@Column(name="field_type")//Field Label
		private String serviceFieldType;
		
		@Column(name="name")//Field name
		private String fieldName;
		
		@Column(name="field_details")//Field Data
		private String fieldData;
		
		@Column(name="field_description")//Field Description
		private String fieldDescription;
		
		@Column(name="status")
		private String status;
		
		@Column(name="created_at")
		private String createdAt;
		
		@Column(name="updated_at")
		private String updatedAt;
}
