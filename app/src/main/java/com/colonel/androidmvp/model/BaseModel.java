package com.colonel.androidmvp.model;

import com.colonel.androidmvp.api.RetrofitManager;
import com.colonel.androidmvp.presenter.BasePresenterImpl;

/**
 * Created by mi on 17-11-8.
 */

public class BaseModel {

    protected RetrofitManager retrofitManager;

    public BaseModel() {
        retrofitManager = RetrofitManager.builder();
    }
}
