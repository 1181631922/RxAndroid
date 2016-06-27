package com.fanyafeng.rxandroid.hong9.service;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by fanyafeng on 16/6/24.
 */
public class ServiceFactory {
    public static <T> T createServiceFrom(final Class<T> serviceClass, String baseUrl) {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return adapter.create(serviceClass);
    }
}
