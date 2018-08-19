package com.ruslanlyalko.agency.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class Order extends BaseModel implements Parcelable {

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {return new Order(source);}

        @Override
        public Order[] newArray(int size) {return new Order[size];}
    };
    private static final int DURATION_MAX = 25;
    private static final int CHILDREN_MAX = 50;
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

    public Order() {
        duration = 3;
        children = 7;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 0);
        userId = FirebaseAuth.getInstance().getUid();
        date = calendar.getTime();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public String getDurationFormatted() {
        return String.format(Locale.US, "%.1f h", (getDuration() * 0.5f));
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(final int children) {
        this.children = children;
    }

    public String getChildrenFormatted() {
        return String.valueOf(getChildren());
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(final int income) {
        this.income = income;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(final int expense) {
        this.expense = expense;
    }

    public boolean getMk() {
        return mk;
    }

    public void setMk(final boolean mk) {
        this.mk = mk;
    }

    public boolean getAqua() {
        return aqua;
    }

    public void setAqua(final boolean aqua) {
        this.aqua = aqua;
    }

    public boolean getPinata() {
        return pinata;
    }

    public void setPinata(final boolean pinata) {
        this.pinata = pinata;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(final Equipment equipment) {
        this.equipment = equipment;
    }

    public String getFile() {
        return file;
    }

    public void setFile(final String file) {
        this.file = file;
    }

    public String getLocalFile() {
        return localFile;
    }

    public void setLocalFile(final String localFile) {
        this.localFile = localFile;
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

    public void incrementDuration() {
        if (duration < DURATION_MAX)
            duration += 1;
    }

    public void decrementDuration() {
        if (duration > 0)
            duration -= 1;
    }

    public void incrementChildren() {
        if (children < CHILDREN_MAX)
            children += 1;
    }

    public void decrementChildren() {
        if (children > 0)
            children -= 1;
    }

    public String getExpenseFormatted() {
        return String.valueOf(expense);
    }

    public String getIncomeFormatted() {
        return String.valueOf(income);
    }

    public String getClientNamePhone() {
        if (name == null) name = "";
        if (phone == null) phone = "";
        return String.format("%s %s", name, phone);
    }
}
