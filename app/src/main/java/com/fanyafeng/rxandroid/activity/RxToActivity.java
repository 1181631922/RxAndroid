package com.fanyafeng.rxandroid.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.rxandroid.BaseActivity;
import com.fanyafeng.rxandroid.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxToActivity extends BaseActivity {
    private TextView tvSubText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_to);
        title = "RxAndroid跳转界面";
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
        tvSubText = (TextView) findViewById(R.id.tvSubText);
    }

    private void initData() {


    }

    private void initData1() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };


        //注册观察活动
        Observable<String> stringObservable = Observable.create(onSubscribe);
        //分发订阅信息
        stringObservable.observeOn(AndroidSchedulers.mainThread());
        stringObservable.subscribe(textSubscriber);
        stringObservable.subscribe(toastSubscriber);

        hello("Fan", "Ya", "Feng");
        hello("Beijing", "Chaoyang");

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                //创建一个有序的list，onnext会依次调用
                subscriber.onNext(hello("nihao"));
                subscriber.onCompleted();
            }
        });
    }

    private void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
//                System.out.println("Hello " + s + "!");
                String s1 = tvSubText.getText().toString().trim();
                tvSubText.setText(s1 + s);
            }
        });
    }

    Observable.OnSubscribe onSubscribe = new Observable.OnSubscribe<String>() {

        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext(hello("World"));//发送事件
            subscriber.onCompleted();//事件完成
        }
    };

    Subscriber<String> toastSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Toast.makeText(RxToActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnOperate1:
                operate1();
                break;
            case R.id.btnOperate2:
                operate2();
                break;
        }
    }

    private void operate2() {
        Observable<String> stringObservable = Observable.just("Hello","World");
        stringObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("rxto", s);
                tvSubText.setText(s);
            }
        });
    }

    private void operate1() {
        //注册观察活动
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(hello("Hello World"));//发送事件
                subscriber.onCompleted();//事件完成
            }
        });
//        stringObservable.observeOn(AndroidSchedulers.mainThread());
//        stringObservable.observeOn(Schedulers.io());
//        stringObservable.observeOn(Schedulers.computation());
//        stringObservable.observeOn(Schedulers.newThread());
        stringObservable.subscribe(textSubscriber);//有更新ui的默认主线程，不用去指定线程
    }

    private String hello(String names) {
        Log.d("rxto", names);
        return names;
    }

    //订阅者,接受字符串,修改控件
    Subscriber<String> textSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            tvSubText.setText(s);
        }
    };
}
