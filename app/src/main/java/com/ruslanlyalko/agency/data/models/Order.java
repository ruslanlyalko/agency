package com.ruslanlyalko.agency.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class Order extends BaseModel implements Parcelable {

    private String userId;
    private String phone;
    private String name;
    private String file;
    private String localFile;
    private String description;
    private Date date;
    private int duration;
    private int children;
    private int income;
    private int expense;
    private boolean mk;
    private boolean aqua;
    private boolean pinata;
    private Equipment equipment;

    public Order() {}

    public String getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public int getChildren() {
        return children;
    }

    public int getIncome() {
        return income;
    }

    public int getExpense() {
        return expense;
    }

    public boolean getMk() {
        return mk;
    }

    public boolean getAqua() {
        return aqua;
    }

    public boolean getPinata() {
        return pinata;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public String getFile() {
        return file;
    }

    public String getLocalFile() {
        return localFile;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.userId);
        dest.writeString(this.phone);
        dest.writeString(this.name);
        dest.writeString(this.file);
        dest.writeString(this.localFile);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeString(this.description);
        dest.writeInt(this.duration);
        dest.writeInt(this.children);
        dest.writeInt(this.income);
        dest.writeInt(this.expense);
        dest.writeByte(this.mk ? (byte) 1 : (byte) 0);
        dest.writeByte(this.aqua ? (byte) 1 : (byte) 0);
        dest.writeByte(this.pinata ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.equipment, flags);
    }

    protected Order(Parcel in) {
        super(in);
        this.userId = in.readString();
        this.phone = in.readString();
        this.name = in.readString();
        this.file = in.readString();
        this.localFile = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.description = in.readString();
        this.duration = in.readInt();
        this.children = in.readInt();
        this.income = in.readInt();
        this.expense = in.readInt();
        this.mk = in.readByte() != 0;
        this.aqua = in.readByte() != 0;
        this.pinata = in.readByte() != 0;
        this.equipment = in.readParcelable(Equipment.class.getClassLoader());
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {return new Order(source);}

        @Override
        public Order[] newArray(int size) {return new Order[size];}
    };
}
