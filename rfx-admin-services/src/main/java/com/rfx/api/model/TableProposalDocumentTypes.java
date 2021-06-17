package com.rfx.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="proposal_document_types")
@Table(name = "proposal_document_types")
public class TableProposalDocumentTypes {
	 @Id
	    @GeneratedValue
	    private int id;
	    @Column
	    private String proposal_document_type_id;
	    @Column
	    private String name;
	    @Column
	    private String description;
	    @Column
	    String code;
		@Column
	    String status;
	    @Column
	    private String effectivedate;
	    @Column
	    private String updated_at;
	    @Column
	    private String created_at;

	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDescription() {
	        return description;
	    }
	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getCode() {
	        return code;
	    }
	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getStatus() {
	        return status;
	    }
	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getEffectivedate() {
	        return effectivedate;
	    }
	    public void setEffectivedate(String effectivedate) {
	        this.effectivedate = effectivedate;
	    }
	    public String getProposalDocumentTypeId() {
			return proposal_document_type_id;
		}
		public void setProposalDocumentTypeId(String proposal_document_type_id) {
			this.proposal_document_type_id = proposal_document_type_id;
		}
	
		public String getUpdatedAt() {
			return updated_at;
		}
		public void setUpdatedAt(String updated_at) {
			this.updated_at = updated_at;
		}
		public String getCreatedAt() {
			return created_at;
		}
		public void setCreatedAt(String created_at) {
			this.created_at = created_at;
		}
	

}
