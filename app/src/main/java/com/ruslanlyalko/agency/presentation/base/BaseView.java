package com.ruslanlyalko.agency.presentation.base;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public interface BaseView<P extends BasePresenter> {

    void showMessage(String text);
}
