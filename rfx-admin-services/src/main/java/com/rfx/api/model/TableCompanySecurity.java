package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="company_security")
@Table(name = "company_security")
public class TableCompanySecurity {
	
	@Id
    @GeneratedValue
    private int id;
	
    @Column(name="business_id")
    private String businessUnitId;
    
    @Column(name="business_unit_type_id")
	private String businessUnitTypeId ;
    
    @Column(name="security_name")
	private String securityName;  
    
    @Column(name="security_details")
	private String securityDetails; 
    
    @Column(name="certifications")
	private String certifications; 
    
    @Column(name="status")
    private String status; 
    
    @Column(name="created_at")
    private String createdAt; 
    
    @Column(name="updated_at")
    private String updatedAt; 

}
