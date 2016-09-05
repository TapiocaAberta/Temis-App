package com.sjcdigital.temis.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Links implements Parcelable {
    public Self self;
    public First first;
    public Next next;
    public Last last;

    protected Links(Parcel in) {
        self = in.readParcelable(Self.class.getClassLoader());
        first = in.readParcelable(First.class.getClassLoader());
        next = in.readParcelable(Next.class.getClassLoader());
        last = in.readParcelable(Last.class.getClassLoader());
    }

    public static final Creator<Links> CREATOR = new Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel in) {
            return new Links(in);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(self, flags);
        dest.writeParcelable(first, flags);
        dest.writeParcelable(next, flags);
        dest.writeParcelable(last, flags);
    }
}
