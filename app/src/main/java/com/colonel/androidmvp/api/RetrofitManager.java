package com.colonel.androidmvp.api;

import com.colonel.androidmvp.MvpApplication;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mi on 17-11-8.
 */

public class RetrofitManager {

    //地址
    public static final String BASE_PHONENUMINFO_URL = "http://api.k780.com:88";

    //短缓存有效期为1分钟
    public static final int CACHE_STALE_SHORT = 60;
    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;

    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private OkHttpClient okHttpClient;

    private final PhoneNumService phoneNumService;

    public static RetrofitManager builder() {
        return new RetrofitManager();
    }

    public PhoneNumService getPhoneNumService() {
        return phoneNumService;
    }

    private RetrofitManager() {
        initOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_PHONENUMINFO_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        phoneNumService = retrofit.create(PhoneNumService.class);
    }

    private void initOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (RetrofitManager.this) {
                if (okHttpClient == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    Cache cache = new Cache(new File(MvpApplication.getContext().getCacheDir(), "httpCache"), 1024 * 1024 * 10);
                    okHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(5000, TimeUnit.MILLISECONDS)
                            .build();
                }
            }
        }
    }
}
