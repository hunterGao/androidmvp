package com.colonel.androidmvp.model;

import android.content.Context;
import android.util.Log;

import com.colonel.androidmvp.api.PhoneNumService;
import com.colonel.androidmvp.bean.PhoneNumInfo;
import com.colonel.androidmvp.presenter.IBasePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mi on 17-11-8.
 */

public class PhoneModeImpl extends BaseModel {

    private static final String TAG = "PhoneModeImpl";

    private Context context;
    private PhoneNumService phoneNumService;

    public PhoneModeImpl(Context context) {
        super();
        this.context = context;
        phoneNumService = retrofitManager.getPhoneNumService();
    }

    public void loadPhoneNumInfo(String phoneNum, final IBasePresenter<PhoneNumInfo> presenter) {
        phoneNumService.getBeforeNews("phone.get", phoneNum, "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhoneNumInfo>() {

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ");
                        presenter.requestError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                        presenter.requestComplete();
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                        presenter.beforeRequest();
                    }

                    @Override
                    public void onNext(PhoneNumInfo info) {
                        Log.e(TAG, "onNext: ");
                        if (info == null) {
                            return;
                        }

                        if (info.getSuccess().equals("1")) {
                            presenter.requestSuccess(info);
                        } else if (info.getSuccess().equals("0")) {
                            presenter.requestError(info.getResult().getMsg());
                        } else {
                            presenter.requestError("获取数据失败");
                        }
                    }
                });
    }
}
