package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Law implements Parcelable {
    @SerializedName("_embedded")
    public LawEmbedded embedded;

    protected Law(Parcel in) {
        embedded = in.readParcelable(LawEmbedded.class.getClassLoader());
    }

    public static final Creator<Law> CREATOR = new Creator<Law>() {
        @Override
        public Law createFromParcel(Parcel in) {
            return new Law(in);
        }

        @Override
        public Law[] newArray(int size) {
            return new Law[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(embedded, i);
    }
}
