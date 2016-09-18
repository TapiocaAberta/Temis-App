package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Laws implements Parcelable {
    @SerializedName("_embedded")
    public Embedded embedded;
    @SerializedName("_links")
    public Links links;

    protected Laws(Parcel in) {
        embedded = in.readParcelable(Embedded.class.getClassLoader());
        links = in.readParcelable(Links.class.getClassLoader());
    }

    public static final Creator<Laws> CREATOR = new Creator<Laws>() {
        @Override
        public Laws createFromParcel(Parcel in) {
            return new Laws(in);
        }

        @Override
        public Laws[] newArray(int size) {
            return new Laws[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(embedded, i);
        parcel.writeParcelable(links, i);
    }
}
