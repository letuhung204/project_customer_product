package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="company_products")
@Table(name = "company_products")
public class TableCompanyProducts {
	
	    @Id
	    @GeneratedValue
		@Column(name="id")
		private int id;
	    @Column(name="business_unit_id")
		private String businessUnitId ;
	    @Column(name="business_unit_type_id")
		private String businessUnitTypeId ;
	    @Column(name="prod_name")
		private String productName ;
	    @Column(name="prod_function")
		private String productFunction;
	    @Column(name="prod_details")
		private String productDetails;
	    @Column(name="nigp_unpsc_codes")
		private String nigpUnpscCodes;
	    @Column(name="status")
		private String status;
	    @Column(name="created_at")
		private String createdAt;
	    @Column(name="updated_at")
		private String updatedAt;
	
	
}
