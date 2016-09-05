package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class First implements Parcelable {
    public String href;

    protected First(Parcel in) {
        href = in.readString();
    }

    public static final Creator<First> CREATOR = new Creator<First>() {
        @Override
        public First createFromParcel(Parcel in) {
            return new First(in);
        }

        @Override
        public First[] newArray(int size) {
            return new First[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(href);
    }
}
