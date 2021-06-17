package com.rfx.api.model;

/**
 * Created by hubinotech on 21/04/20.
 */

import javax.persistence.*;


@Entity(name = "refresh_token")
@Table(name = "refresh_token")
public class TableRefreshToken {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String token;
    @Column
    private String expired;
    @Column
    private String user_email;
	@Column
    private String createdAt;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUserEmail() {
  		return user_email;
  	}
  	public void setUserEmail(String user_email) {
  		this.user_email = user_email;
  	}
   

    public String getExpired() {
        return expired;
    }
    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
