package com.sjcdigital.temis.domain.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "author")
public class Author implements Serializable {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String politicalParty;
    @DatabaseField
    private String info;
    @DatabaseField
    private String email;
    @DatabaseField
    private String legislature;
    @DatabaseField
    private String workplace;
    @DatabaseField
    private String photo;
    @DatabaseField
    private String phone;
    @DatabaseField
    private boolean notFound;

    private Author() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoliticalParty() {
        return politicalParty;
    }

    public String getInfo() {
        return info;
    }

    public String getEmail() {
        return email;
    }

    public String getLegislature() {
        return legislature;
    }

    public String getWorkplace() {
        return workplace;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isNotFound() {
        return notFound;
    }
}