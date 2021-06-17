package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="company_process")
@Table(name = "company_process")
public class TableCompanyProcess {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="business_id")
    private String businessUnitId;
    @Column(name="business_unit_type_id")
	private String businessUnitTypeId ;
    @Column(name="process_name")
    private String processName;
    @Column(name="process_function")
    private String processFunction;
    @Column(name="process_details")
    private String processDetails;
    @Column(name="nigp_unpsc_codes")
    private String nigpUnpscCodes;
    @Column(name="status")
    private String status;
    @Column(name="created_at")
    private String createdAt;
    @Column(name="updated_at")
    private String updatedAt;
}
