package com.ruslanlyalko.agency.data;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ruslanlyalko.agency.data.models.User;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class DataManagerImpl implements DataManager {

    private static DataManagerImpl mInstance;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    private DataManagerImpl() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
    }

    public static DataManager newInstance() {
        if (mInstance == null)
            mInstance = new DataManagerImpl();
        return mInstance;
    }

    @Override
    public MutableLiveData<User> getUser(final String key) {
        final MutableLiveData<User> userLiveData = new MutableLiveData<>();
        mDatabase.getReference(Config.DB_USERS)
                .child(key)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        userLiveData.postValue(dataSnapshot.getValue(User.class));
                    }

                    @Override
                    public void onCancelled(@NonNull final DatabaseError databaseError) {
                    }
                });
        return userLiveData;
    }
}
