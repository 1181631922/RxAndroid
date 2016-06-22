package com.fanyafeng.rxandroid.hong9.service;


import com.fanyafeng.rxandroid.hong9.network.Urls;
import com.fanyafeng.rxandroid.hong9.response.GetMainResponse;

import retrofit.Call;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by 365rili on 16/6/21.
 */
public interface ApiService {
    @GET(Urls.MAIN_URL)
    Observable<GetMainResponse> getMainData();
}
