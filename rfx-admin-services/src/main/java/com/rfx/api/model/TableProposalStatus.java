package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "proposal_status")
@Table(name = "proposal_status")
public class TableProposalStatus {
	
	@Id
    @GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="proposal_status_id")
	private String proposalStatusId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="code")
    private String code;
	
	@Column(name="status")
    private String status;
	
	@Column(name="effectivedate")
	private String effectivedate;
	
	@Column(name="created_at")
	private String createdAt;
	
	@Column(name="updated_at")
	private String updatedAt;
	
}
