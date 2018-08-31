package com.ruslanlyalko.agency.presentation.base;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ruslanlyalko.agency.data.DataManager;
import com.ruslanlyalko.agency.data.DataManagerImpl;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class BasePresenter<V extends BaseView> {

    private V mBaseActivity;

    private DataManager mDataManager;

    protected BasePresenter() {
        mDataManager = DataManagerImpl.newInstance();
    }

    public void attachView(final V baseActivity) {
        mBaseActivity = baseActivity;
    }

    public void detachView() {
        mBaseActivity = null;
    }

    protected V getView() {
        return mBaseActivity;
    }

    protected FirebaseAuth getAuth() {return FirebaseAuth.getInstance();}

    protected FirebaseUser getCurrentUser() {return FirebaseAuth.getInstance().getCurrentUser();}

    protected DataManager getDataManager() {
        return mDataManager;
    }
}
