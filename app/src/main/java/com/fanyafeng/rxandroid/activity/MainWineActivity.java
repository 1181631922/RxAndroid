package com.fanyafeng.rxandroid.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fanyafeng.rxandroid.BaseActivity;
import com.fanyafeng.rxandroid.R;
import com.fanyafeng.rxandroid.hong9.network.Urls;
import com.fanyafeng.rxandroid.hong9.response.GetMainResponse;
import com.fanyafeng.rxandroid.hong9.service.ApiService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainWineActivity extends BaseActivity {
    private Button btnResponse;
    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wine);
        title = "测试首页数据";

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
        btnResponse = (Button) findViewById(R.id.btnResponse);
        tvResponse = (TextView) findViewById(R.id.tvResponse);
    }

    private void initData() {

    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getMainData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetMainResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("redwine", "请求失败：" + e.toString());
                        tvResponse.setText(e.toString());
                    }

                    @Override
                    public void onNext(GetMainResponse getMainResponse) {
                        tvResponse.setText(getMainResponse.toString());
                        Log.d("redwine", "请求成功：" + getMainResponse.state);
                        Log.d("redwine", "请求成功：" + getMainResponse.data.banner.get(0).id + "数组长度：" + getMainResponse.data.banner.size());
                    }
                });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnResponse:
                getData();
                break;
        }
    }
}
