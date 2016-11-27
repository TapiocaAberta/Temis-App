package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.sjcdigital.temis.util.SerializableCollectionsType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "law")
public class LawList implements Serializable {

    @DatabaseField(id = true)
    private String code;
    @DatabaseField(persisterClass = SerializableCollectionsType.class)
    @SerializedName("author")
    private List<Author> authors;
    @DatabaseField
    private String summary;
    @DatabaseField
    private String type;
    @DatabaseField
    private String desc;
    @DatabaseField
    private String date;
    @DatabaseField
    private String title;
    @DatabaseField
    private String projectLawNumber;
    @DatabaseField
    private int votesCount;
    @DatabaseField
    private int rating;
    @DatabaseField(foreign = true)
    @SerializedName("mAuthor")
    private Author author;

    private LawList() {
    }

    public String getCode() {
        return code;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getSummary() {
        return summary;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getProjectLawNumber() {
        return projectLawNumber;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public int getRating() {
        return rating;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}