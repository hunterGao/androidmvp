package com.colonel.androidmvp.presenter;

import android.content.Context;

import com.colonel.androidmvp.bean.PhoneNumInfo;
import com.colonel.androidmvp.model.PhoneModeImpl;
import com.colonel.androidmvp.view.PhoneNumInfoView;

/**
 * Created by mi on 17-11-8.
 */

public class PhonePresenterImpl extends BasePresenterImpl<PhoneNumInfoView, PhoneNumInfo> {

    private Context context;
    private PhoneModeImpl phoneModel;

    public PhonePresenterImpl(Context context, PhoneNumInfoView view) {
        super(view);
        this.context = context;
        phoneModel = new PhoneModeImpl(context);
    }

    public void getPhoneNumInfo(String phoneNum) {
        phoneModel.loadPhoneNumInfo(phoneNum, this);
    }
}
