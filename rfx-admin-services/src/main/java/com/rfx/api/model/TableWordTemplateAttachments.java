package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "word_template_attachments")
@Table(name = "word_template_attachments")
public class TableWordTemplateAttachments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "descr")
	private String descr;

	@Column(name = "filepath")
	private String filepath;

	@Column(name = "status")
	private String status;
	
	@Column(name = "created_at")
	private String createdAt;

	@Column(name = "updated_at")
	private String updatedAt;
	
	
}
