package com.sjcdigital.temis.model.domain;

import java.util.ArrayList;

public class LawList {
    private String code;
    private ArrayList<Author> author;
    private String desc;
    private String date;
    private String title;
    private String projectLawNumber;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Author> getAuthor() {
        return this.author;
    }

    public void setAuthor(ArrayList<Author> author) {
        this.author = author;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectLawNumber() {
        return this.projectLawNumber;
    }

    public void setProjectLawNumber(String projectLawNumber) {
        this.projectLawNumber = projectLawNumber;
    }
}
