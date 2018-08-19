package com.ruslanlyalko.agency.presentation.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        int viewId = getContentView();
        if (viewId != -1) {
            setContentView(getContentView());
            mUnbinder = ButterKnife.bind(this);
        }
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
        if (mUnbinder != null)
            mUnbinder.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onHomeClicked();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideKeyboard();
    }

    protected int getContentView() {return -1;}

    protected void onHomeClicked() {
        onBackPressed();
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (imm != null && view != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

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
