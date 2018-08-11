package com.ruslanlyalko.agency.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class Order extends BaseModel implements Parcelable {

    private String phone;
    private String name;
    private String place;
    private Date date;
    private List<Equipment> equipments;


    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public Date getDate() {
        return date;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public Order() {}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.phone);
        dest.writeString(this.name);
        dest.writeString(this.place);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeTypedList(this.equipments);
    }

    protected Order(Parcel in) {
        super(in);
        this.phone = in.readString();
        this.name = in.readString();
        this.place = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.equipments = in.createTypedArrayList(Equipment.CREATOR);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {return new Order(source);}

        @Override
        public Order[] newArray(int size) {return new Order[size];}
    };
}
