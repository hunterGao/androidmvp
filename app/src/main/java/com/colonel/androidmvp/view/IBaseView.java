package com.colonel.androidmvp.view;

/**
 * Created by mi on 17-11-8.
 */

public interface IBaseView<T> {

    void showProgress();

    void hideProgress();

    void loadDataSuccess(T data);

    void loadDataError(String errorMsg);
}
