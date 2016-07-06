package com.fanyafeng.rxandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.rxandroid.BaseActivity;
import com.fanyafeng.rxandroid.R;

import rx.Observable;
import rx.functions.Action1;


public class RxAndroidActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android);
        title = "测试rxandroid";
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
        hello("Fan", "Ya", "Feng");
        hello("Beijing", "Chaoyang");


    }

    public static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }
        });

        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }

        for (String s : names) {
            System.out.println(s);
        }

        Observable.from(names).subscribe(s -> {
            System.out.println("Hello " + s + "!");
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnToAndroid:
                startActivity(new Intent(this, RxToActivity.class));
                break;
            case R.id.btnRxDeal:
                startActivity(new Intent(this, RxDealDataActivity.class));
                break;
            case R.id.btnLambda:
                startActivity(new Intent(this, LambdaActivity.class));
                break;
            case R.id.btnNetArgs:
                startActivity(new Intent(this, NetArgsActivity.class));
                break;
        }
    }
}
