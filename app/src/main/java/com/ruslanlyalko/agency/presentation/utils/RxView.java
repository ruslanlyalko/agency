package com.ruslanlyalko.agency.presentation.utils;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Ruslan Lyalko
 * on 19.08.2018.
 */
public class RxView {

    public static MutableLiveData<String> queryTextChanged(TextView view) {
        MutableLiveData<String> result = new MutableLiveData<>();
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                result.postValue(s.toString());
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });
        return result;
    }

    public static MutableLiveData<Integer> queryIntChanged(TextView view) {
        MutableLiveData<Integer> result = new MutableLiveData<>();
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                int res = 0;
                try {
                    res = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                result.postValue(res);
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });
        return result;
    }

    public static LiveData<Boolean> queryCheckedChanged(final CheckBox view) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        view.setOnCheckedChangeListener((buttonView, isChecked) -> result.postValue(isChecked));
        return result;
    }
}
