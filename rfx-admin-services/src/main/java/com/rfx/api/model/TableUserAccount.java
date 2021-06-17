package com.rfx.api.model;


import javax.persistence.*;


@Entity(name = "user_accounts")
@Table(name = "user_accounts")
public class TableUserAccount {
	
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String user_id;
    @Column
    private String business_id;
    @Column
    private String rfx_category_id;
	@Column
    private int role_id;
	@Column
    private String email;
	@Column
    private String name;
	@Column
    private String password;
    @Column
    private String status;
    @Column
    private String created_at;
	@Column
    private String updated_at;
	@Column
	private String code;
	@Column
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdatedAt() {
		return updated_at;
	}
	public void setUpdatedAt(String updated_at) {
		this.updated_at = updated_at;
	}
	
    public String getUserId() {
		return user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	public String getBusinessId() {
		return business_id;
	}
	public void setBusinessId(String business_id) {
		this.business_id = business_id;
	}
	public int getRoleId() {
		return role_id;
	}
	public void setRoleId(int role_id) {
		this.role_id = role_id;
	}
    public String getRfxCategoryId() {
		return rfx_category_id;
	}
	public void setRfxCategoryId(String rfx_category_id) {
		this.rfx_category_id = rfx_category_id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

   
   
}
