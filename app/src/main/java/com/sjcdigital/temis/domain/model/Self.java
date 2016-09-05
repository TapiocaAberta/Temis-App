package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Self implements Parcelable {
    public String href;

    protected Self(Parcel in) {
        href = in.readString();
    }

    public static final Creator<Self> CREATOR = new Creator<Self>() {
        @Override
        public Self createFromParcel(Parcel in) {
            return new Self(in);
        }

        @Override
        public Self[] newArray(int size) {
            return new Self[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.href);
    }
}
