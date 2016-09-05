package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Last implements Parcelable{
    public String href;

    protected Last(Parcel in) {
        href = in.readString();
    }

    public static final Creator<Last> CREATOR = new Creator<Last>() {
        @Override
        public Last createFromParcel(Parcel in) {
            return new Last(in);
        }

        @Override
        public Last[] newArray(int size) {
            return new Last[size];
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
