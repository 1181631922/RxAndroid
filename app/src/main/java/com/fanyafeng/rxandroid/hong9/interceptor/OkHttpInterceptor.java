package com.fanyafeng.rxandroid.hong9.interceptor;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


/**
 * Created by fanyafeng on 16/6/29.
 */
public class OkHttpInterceptor {

    public final static String USER_AGENT = "Android";
    public final static String GZIP = "gzip";

    public static String getGzip() {
        return GZIP;
    }

    public static String getUserAgent() {
        return USER_AGENT;
    }

    private static OkHttpClient okHttpClient=new OkHttpClient();

    public static OkHttpClient getHttpClient() {
//        if (okHttpClient == null) {
//            okHttpClient = new OkHttpClient();
//        }
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("User-Agent", getUserAgent())
                        .header("Accept-encoding", getGzip())
                        .method(original.method(), original.body())
                        .build();
                Response response = chain.proceed(request);
                return response;
            }
        });

        return okHttpClient;
    }


}
