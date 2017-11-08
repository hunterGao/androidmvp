package com.colonel.androidmvp.presenter;

/**
 * Created by mi on 17-11-8.
 */

public interface IBasePresenter<T> {

    void beforeRequest();

    void requestComplete();

    void requestSuccess(T data);

    void requestError(String error);

}
