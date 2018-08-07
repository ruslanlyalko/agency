package com.ruslanlyalko.agency.presentation.ui.profile;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.User;
import com.ruslanlyalko.agency.presentation.base.BaseActivity;

import butterknife.BindView;

public class ProfileActivity extends BaseActivity<ProfilePresenter> implements ProfileView {

    @BindView(R.id.text_view) TextView mTextView;

    public static Intent getLaunchIntent(final BaseActivity activity) {
        return new Intent(activity, ProfileActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_profile;
    }

    @Override
    protected void initPresenter(final Intent intent) {
        setPresenter(new ProfilePresenter());
    }

    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        getPresenter().fetchUser();
    }

    @Override
    public void showUser(final MutableLiveData<User> user) {
        user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User user) {
                if (user != null)
                    mTextView.setText(user.getName());
            }
        });
    }
}
