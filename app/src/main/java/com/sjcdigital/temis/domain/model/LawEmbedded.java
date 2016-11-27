package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class LawEmbedded implements Parcelable {
    public ArrayList<LawList> lawList;

    protected LawEmbedded(Parcel in) {
        this.lawList = in.readArrayList(LawList.class.getClassLoader());
    }

    public static final Creator<LawEmbedded> CREATOR = new Creator<LawEmbedded>() {
        @Override
        public LawEmbedded createFromParcel(Parcel in) {
            return new LawEmbedded(in);
        }

        @Override
        public LawEmbedded[] newArray(int size) {
            return new LawEmbedded[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.lawList);
    }
}
