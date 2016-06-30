package com.fanyafeng.rxandroid.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.rxandroid.R;
import com.fanyafeng.rxandroid.BaseActivity;
import com.fanyafeng.rxandroid.bean.IpInfoBean;
import com.fanyafeng.rxandroid.retrofit.response.TaoBaoGetIpInfoResponse;
import com.fanyafeng.rxandroid.retrofit.service.ApiService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class NetArgsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_args);
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_net_args);

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //toolbar_center_title.setText(getString(R.string.title_activity_net_args));
    }

    //初始化UI控件
    private void initView() {

    }

    //初始化数据
    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getToken()
                .flatMap(new Func1<String, Observable<TaoBaoGetIpInfoResponse>>() {
                    @Override
                    public Observable<TaoBaoGetIpInfoResponse> call(String s) {
                        return apiService.getIpinfoBean(s,"ip");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaoBaoGetIpInfoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TaoBaoGetIpInfoResponse taoBaoGetIpInfoResponse) {

                    }
                });
    }

}
