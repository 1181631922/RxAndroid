package com.fanyafeng.rxandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fanyafeng.rxandroid.BaseActivity;
import com.fanyafeng.rxandroid.R;
import com.fanyafeng.rxandroid.netutil.Urls;
import com.fanyafeng.rxandroid.retrofit.response.TaoBaoGetIpInfoResponse;
import com.fanyafeng.rxandroid.retrofit.service.ApiService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RetrofitActivity extends BaseActivity {
    private TextView tvShowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        initData();
    }

    private void initView() {
        tvShowInfo = (TextView) findViewById(R.id.tvShowInfo);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnSendRequest:
                getData();
                break;
        }

    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Urls.URL_TAOBAO_BASET)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<TaoBaoGetIpInfoResponse> getIpInfoResponseCall = apiService.getIpInfoBean("63.223.108.42");
        getIpInfoResponseCall.enqueue(new Callback<TaoBaoGetIpInfoResponse>() {
            @Override
            public void onResponse(Response<TaoBaoGetIpInfoResponse> response, Retrofit retrofit) {
                Log.d("main", response.body().data.toString());
                TaoBaoGetIpInfoResponse getIpInfoResponse = response.body();
                tvShowInfo.setText(getIpInfoResponse.data.country);
            }

            @Override
            public void onFailure(Throwable t) {
                tvShowInfo.setText(t.getMessage());
            }
        });
    }

}
