package com.ruslanlyalko.agency.presentation.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView<P> {

    private Unbinder mUnbinder;
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mUnbinder = ButterKnife.bind(this);
        initPresenter(getIntent());
        if (mPresenter == null) {
            throw new RuntimeException("Please init presenter!");
        }
        getPresenter().attachView(this);
        onViewReady(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        getPresenter().detachView();
        mUnbinder.unbind();
        super.onDestroy();
    }

    protected abstract int getContentView();

    protected abstract void initPresenter(final Intent intent);

    protected abstract void onViewReady(final Bundle savedInstanceState);

    public P getPresenter() {
        return mPresenter;
    }

    public void setPresenter(final P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
