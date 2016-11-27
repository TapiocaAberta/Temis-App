package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class AuthorEmbedded implements Parcelable {
    public ArrayList<Author> aldermanList;

    protected AuthorEmbedded(Parcel in) {
        this.aldermanList = in.readArrayList(Author.class.getClassLoader());
    }

    public static final Creator<AuthorEmbedded> CREATOR = new Creator<AuthorEmbedded>() {
        @Override
        public AuthorEmbedded createFromParcel(Parcel in) {
            return new AuthorEmbedded(in);
        }

        @Override
        public AuthorEmbedded[] newArray(int size) {
            return new AuthorEmbedded[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.aldermanList);
    }
}
