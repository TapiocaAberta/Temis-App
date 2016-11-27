package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bruno.santiago on 24/11/2016.
 */

public class Page implements Parcelable {

    @SerializedName("number")
    public int number;
    @SerializedName("totalElements")
    public int total;

    protected Page(Parcel in) {
        this.number = in.readInt();
        this.total = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number);
        dest.writeInt(this.total);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Page> CREATOR = new Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel in) {
            return new Page(in);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };
}
