package com.ruslanlyalko.agency.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class BaseModel implements Parcelable {

    String key;

    public String getKey() {
        return key;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {dest.writeString(this.key);}

    public BaseModel() {}

    protected BaseModel(Parcel in) {this.key = in.readString();}
}
