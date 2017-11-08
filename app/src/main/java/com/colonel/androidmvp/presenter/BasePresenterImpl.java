package com.colonel.androidmvp.presenter;

import com.colonel.androidmvp.view.IBaseView;

/**
 * @param <T> 视图接口对象
 * @param <V> 业务请求放回的具体对象
 * Created by mi on 17-11-8.
 */
public class BasePresenterImpl<T extends IBaseView, V> implements IBasePresenter<V> {

    private IBaseView baseView;

    public BasePresenterImpl(T view) {
        baseView = view;
    }

    @Override
    public void beforeRequest() {
        baseView.showProgress();
    }

    @Override
    public void requestComplete() {
        baseView.hideProgress();
    }

    @Override
    public void requestSuccess(V data) {
        baseView.loadDataSuccess(data);
    }

    @Override
    public void requestError(String errorMsg) {
        baseView.loadDataError(errorMsg);
    }
}
