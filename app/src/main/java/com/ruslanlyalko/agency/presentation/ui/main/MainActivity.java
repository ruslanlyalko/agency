package com.ruslanlyalko.agency.presentation.ui.main;

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

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.text_view) TextView mTextView;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter(final Intent intent) {
        setPresenter(new MainPresenter());
    }

    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        getPresenter().showSomeMessage();
    }

    @Override
    public void showMessage(final String text) {
        mTextView.setText(text);
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
