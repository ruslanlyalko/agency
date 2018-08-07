package com.ruslanlyalko.agency.data;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.agency.data.models.User;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public interface DataManager {

    MutableLiveData<User> getMyUser();

    MutableLiveData<User> getUser(String key);
}
