package com.fanyafeng.rxandroid.retrofit.service;

import com.fanyafeng.rxandroid.retrofit.response.TaoBaoGetIpInfoResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by 365rili on 16/6/16.
 */
public interface ApiService {

    @GET("/service/getIpInfo.php")
    Call<TaoBaoGetIpInfoResponse> getIpInfoBean(@Query("ip") String ip);

    //    下方测试
    @GET("/token")
    Observable<String> getToken();

    @GET("/user")
    Observable<TaoBaoGetIpInfoResponse> getIpinfoBean(@Query("token") String token, @Query("ip") String ip);
}
