package com.ruslanlyalko.agency.data.models;

import java.util.Date;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class User extends BaseModel {

    private String name;
    private String email;
    private String avatar;
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
}
