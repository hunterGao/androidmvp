package com.colonel.androidmvp.api;

import com.colonel.androidmvp.bean.PhoneNumInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by mi on 17-11-8.
 */

public interface PhoneNumService {

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("/")
    Observable<PhoneNumInfo> getBeforeNews(@Query("app") String app,
                                           @Query("phone") String phone,
                                           @Query("appkey") String appkey,
                                           @Query("sign") String sign,
                                           @Query("format") String format);
}
