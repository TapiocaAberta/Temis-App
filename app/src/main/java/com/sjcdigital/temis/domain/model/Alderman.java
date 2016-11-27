package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bruno.santiago on 23/11/2016.
 */

public class Alderman implements Parcelable {

    @SerializedName("_embedded")
    public AuthorEmbedded embedded;
    @SerializedName("page")
    public Page page;

    protected Alderman(Parcel in) {
        embedded = in.readParcelable(AuthorEmbedded.class.getClassLoader());
        page = in.readParcelable(Page.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(embedded, flags);
        dest.writeParcelable(page, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Alderman> CREATOR = new Creator<Alderman>() {
        @Override
        public Alderman createFromParcel(Parcel in) {
            return new Alderman(in);
        }

        @Override
        public Alderman[] newArray(int size) {
            return new Alderman[size];
        }
    };
}
