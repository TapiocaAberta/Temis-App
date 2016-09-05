package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Author implements Parcelable {
    public String id;
    public String name;
    public String politicalParty;
    public String info;
    public String email;
    public String legislature;
    public String workplace;
    public String photo;
    public String phone;
    public boolean notFound;
    public ArrayList<Links> links;

    protected Author(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.politicalParty = in.readString();
        this.info = in.readString();
        this.email = in.readString();
        this.legislature = in.readString();
        this.workplace = in.readString();
        this.photo = in.readString();
        this.phone = in.readString();
        this.notFound = in.readByte() != 0;
        this.links = in.readArrayList(Links.class.getClassLoader());
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.politicalParty);
        dest.writeString(this.info);
        dest.writeString(this.email);
        dest.writeString(this.legislature);
        dest.writeString(this.workplace);
        dest.writeString(this.photo);
        dest.writeString(this.phone);
        dest.writeByte((byte) (this.notFound ? 1 : 0));
        dest.writeList(this.links);
    }
}
