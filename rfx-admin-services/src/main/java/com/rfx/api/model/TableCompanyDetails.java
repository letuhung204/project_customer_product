package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "company_details")
@Table(name = "company_details")
public class TableCompanyDetails {
	@Id
    @GeneratedValue
    private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="website_url")
	private String websiteUrl;
	
	@Column(name="legal_struct")
	private String legalStruct;
	
	@Column(name="background")
	private String background;
	
	@Column(name="history")
	private String history;
	
	@Column(name="current_and_history_of_revenue_growth")
	private String revenueGrowth;
	
	@Column(name="key_staff_profile")
	private String keyStaffProfile;
	
	@Column(name="head_quater_address")
	private String headQuaterAddress;
	
	@Column(name="capability_references")
	private String capabilityReferences;
	
	@Column(name="company_clients")
	private String companyClients;

	@Column(name="name_desc")
	private String nameDesc;
	
	@Column(name="website_url_desc")
	private String websiteUrlDesc;
	
	@Column(name="contact_desc")
	private String contactDesc;
	
	@Column(name="legal_struct_desc")
	private String legalStructDesc;
	
	@Column(name="background_desc")
	private String backgroundDesc;
	
	@Column(name="history_desc")
	private String historyDesc;
	
	@Column(name="current_and_history_of_revenue_growth_desc")
	private String revenueGrowthDesc;
	
	@Column(name="key_staff_profile_desc")
	private String keyStaffProfileDesc;
	
	@Column(name="head_quater_address_desc")
	private String headQuaterAddressDesc;
	
	@Column(name="capability_references_desc")
	private String capabilityReferencesDesc;
	
	@Column(name="company_clients_desc")
	private String companyClientsDesc;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_at")
	private String  createdAt;
	
	@Column(name="updated_at")
	private String  updatedAt;
	

}
