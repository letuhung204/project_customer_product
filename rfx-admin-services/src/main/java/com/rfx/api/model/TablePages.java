package com.rfx.api.model;

import javax.persistence.*;

@Entity(name = "pages")
@Table(name = "pages")
public class TablePages {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private String pages;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPages() {
        return pages;
    }
    public void setPages(String pages) {
        this.pages = pages;
    }
}
