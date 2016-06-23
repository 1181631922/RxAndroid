package com.fanyafeng.rxandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fanyafeng.rxandroid.BaseActivity;
import com.fanyafeng.rxandroid.R;
import com.fanyafeng.rxandroid.hong9.activity.RedWineActivity;
import com.fanyafeng.rxandroid.netutil.Urls;
import com.fanyafeng.rxandroid.retrofit.response.TaoBaoGetIpInfoResponse;
import com.fanyafeng.rxandroid.retrofit.service.ApiService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnRetrofit:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;
            case R.id.btnRxAndroid:
                startActivity(new Intent(this, RxAndroidActivity.class));
                break;
            case R.id.btnRedWine:
                startActivity(new Intent(this, RedWineActivity.class));
                break;
            case R.id.btnMainWine:
                startActivity(new Intent(this, MainWineActivity.class));
                break;
        }
    }


}
