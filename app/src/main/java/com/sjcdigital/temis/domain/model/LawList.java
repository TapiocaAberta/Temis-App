package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class LawList implements Parcelable {
    public String code;
    public ArrayList<Author> author;
    public String desc;
    public String date;
    public String title;
    public String projectLawNumber;

    protected LawList(Parcel in) {
        this.code = in.readString();
        this.desc = in.readString();
        this.date = in.readString();
        this.title = in.readString();
        this.projectLawNumber = in.readString();
        this.author = in.readArrayList(Author.class.getClassLoader());
    }

    public static final Creator<LawList> CREATOR = new Creator<LawList>() {
        @Override
        public LawList createFromParcel(Parcel in) {
            return new LawList(in);
        }

        @Override
        public LawList[] newArray(int size) {
            return new LawList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.code);
        parcel.writeString(this.desc);
        parcel.writeString(this.date);
        parcel.writeString(this.title);
        parcel.writeString(this.projectLawNumber);
        parcel.writeList(this.author);
    }
}
