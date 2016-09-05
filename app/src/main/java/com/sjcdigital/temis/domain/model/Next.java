package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Next implements Parcelable {
    public String href;

    protected Next(Parcel in) {
        href = in.readString();
    }

    public static final Creator<Next> CREATOR = new Creator<Next>() {
        @Override
        public Next createFromParcel(Parcel in) {
            return new Next(in);
        }

        @Override
        public Next[] newArray(int size) {
            return new Next[size];
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
