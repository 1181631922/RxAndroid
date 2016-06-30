package com.fanyafeng.rxandroid.hong9.service;


import com.fanyafeng.rxandroid.hong9.network.Urls;
import com.fanyafeng.rxandroid.hong9.response.GetMainResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by 365rili on 16/6/21.
 */
public interface ApiService {
    @GET(Urls.MAIN_URL)
    Observable<GetMainResponse> getMainData();

    @GET(Urls.MAIN_URL)
//增加头
    Observable<GetMainResponse> getHeaderMainData(@Header("User-Agent") String userAgent, @Header("Accept-encoding") String gzip);

}
