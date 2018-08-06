package com.ruslanlyalko.agency.data.models;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class User extends BaseModel {

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
