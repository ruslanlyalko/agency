package com.ruslanlyalko.agency.data.models;

import java.util.Date;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class Order extends BaseModel {

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
}
