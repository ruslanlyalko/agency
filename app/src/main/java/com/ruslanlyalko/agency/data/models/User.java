package com.ruslanlyalko.agency.data.models;

import android.os.Parcel;

import java.util.Date;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class User extends BaseModel {

    private String name;
    private String email;
    private String phone;
    private String avatar;
    private String ownerId;
    private Date birthday;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public User() {}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.avatar);
        dest.writeString(this.ownerId);
        dest.writeLong(this.birthday != null ? this.birthday.getTime() : -1);
    }

    protected User(Parcel in) {
        super(in);
        this.name = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.avatar = in.readString();
        this.ownerId = in.readString();
        long tmpBirthday = in.readLong();
        this.birthday = tmpBirthday == -1 ? null : new Date(tmpBirthday);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {return new User(source);}

        @Override
        public User[] newArray(int size) {return new User[size];}
    };
}
