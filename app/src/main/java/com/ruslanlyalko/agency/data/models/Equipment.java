package com.ruslanlyalko.agency.data.models;

import android.os.Parcel;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class Equipment extends BaseModel {

    private String title;

    public String getTitle() {
        return title;
    }

    public Equipment() {}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.title);
    }

    protected Equipment(Parcel in) {
        super(in);
        this.title = in.readString();
    }

    public static final Creator<Equipment> CREATOR = new Creator<Equipment>() {
        @Override
        public Equipment createFromParcel(Parcel source) {return new Equipment(source);}

        @Override
        public Equipment[] newArray(int size) {return new Equipment[size];}
    };
}
