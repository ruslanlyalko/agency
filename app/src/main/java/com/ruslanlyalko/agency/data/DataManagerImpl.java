package com.ruslanlyalko.agency.data;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ruslanlyalko.agency.data.models.Order;
import com.ruslanlyalko.agency.data.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class DataManagerImpl implements DataManager {

    private static final String TAG = "DataManager";
    private static DataManagerImpl mInstance;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    private DataManagerImpl() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        // keep all users data synced
        if (mAuth.getCurrentUser() != null) {
            mDatabase.getReference(Config.DB_USERS_DATA)
                    .child(mAuth.getCurrentUser().getUid())
                    .keepSynced(true);
        }
    }

    public static DataManager newInstance() {
        if (mInstance == null)
            mInstance = new DataManagerImpl();
        return mInstance;
    }

    @Override
    public MutableLiveData<User> getMyUser() {
        return getUser(mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : null);
    }

    @Override
    public MutableLiveData<User> getUser(final String key) {
        final MutableLiveData<User> userLiveData = new MutableLiveData<>();
        if (TextUtils.isEmpty(key)) {
            Log.w(TAG, "getUser has wrong argument");
            return userLiveData;
        }
        mDatabase.getReference(Config.DB_USERS)
                .child(key)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        Log.d(TAG, "getUser:onDataChange, Key:" + key);
                        userLiveData.postValue(dataSnapshot.getValue(User.class));
                    }

                    @Override
                    public void onCancelled(@NonNull final DatabaseError databaseError) {
                    }
                });
        return userLiveData;
    }

    @Override
    public MutableLiveData<List<Order>> getPastOrders(final String userId) {
        final MutableLiveData<List<Order>> result = new MutableLiveData<>();
        if (TextUtils.isEmpty(userId)) {
            Log.w(TAG, "getPastOrders has wrong argument");
            return result;
        }
        mDatabase.getReference(Config.DB_USERS_DATA)
                .child(userId)
                .child(Config.DB_ORDERS)
                .orderByChild("date/time")
                .endAt(new Date().getTime())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        Log.d(TAG, "getPastOrders:onDataChange, userId:" + userId);
                        List<Order> list = new ArrayList<>();
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            list.add(snap.getValue(Order.class));
                        }
                        result.postValue(list);
                    }

                    @Override
                    public void onCancelled(@NonNull final DatabaseError databaseError) {
                    }
                });
        return result;
    }

    @Override
    public MutableLiveData<List<Order>> getFutureOrders(final String userId) {
        final MutableLiveData<List<Order>> result = new MutableLiveData<>();
        if (TextUtils.isEmpty(userId)) {
            Log.w(TAG, "getFutureOrders has wrong argument");
            return result;
        }
        mDatabase.getReference(Config.DB_USERS_DATA)
                .child(userId)
                .child(Config.DB_ORDERS)
                .orderByChild("date/time")
                .startAt(new Date().getTime())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        Log.d(TAG, "getFutureOrders:onDataChange, userId:" + userId);
                        List<Order> list = new ArrayList<>();
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            list.add(snap.getValue(Order.class));
                        }
                        result.postValue(list);
                    }

                    @Override
                    public void onCancelled(@NonNull final DatabaseError databaseError) {
                    }
                });
        return result;
    }
}
