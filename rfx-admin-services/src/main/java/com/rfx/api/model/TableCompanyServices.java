package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="company_services")
@Table(name = "company_services")
public class TableCompanyServices {
    @Id
    @GeneratedValue
	@Column(name="id")
	private int id;
    @Column(name="business_unit_id")
	private String businessUnitId ;
    @Column(name="business_unit_type_id")
	private String businessUnitTypeId ;
    @Column(name="name")
	private String serviceName ;
    @Column(name="category")
	private String serviceCategory;
    @Column(name="description")
	private String serviceDescription;
    @Column(name="nigp_unpsc_codes")
	private String nigpUnpscCodes;
    @Column(name="status")
	private String status;
    @Column(name="created_at")
	private String createdAt;
    @Column(name="updated_at")
	private String updatedAt;
}
