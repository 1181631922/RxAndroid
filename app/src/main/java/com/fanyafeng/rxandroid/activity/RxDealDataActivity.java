package com.fanyafeng.rxandroid.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.rxandroid.BaseActivity;
import com.fanyafeng.rxandroid.R;
import com.fanyafeng.rxandroid.bean.IpInfoBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class RxDealDataActivity extends BaseActivity {

    String[] wordList = {"My", "name", "is", "Fan", "Ya", "Feng"};
    List<String> stringList = Arrays.asList(wordList);
    List<IpInfoBean> ipInfoBeanList;

    private TextView tvDealData;
    private TextView tvDealData1;
    private TextView tvDealData2;
    private TextView tvDealData3;
    private TextView tvDealData4;
    private TextView tvDealData5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_deal_data);
        title = "使用rxjava处理数据";
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
        tvDealData = (TextView) findViewById(R.id.tvDealData);
        tvDealData1 = (TextView) findViewById(R.id.tvDealData1);
        tvDealData2 = (TextView) findViewById(R.id.tvDealData2);
        tvDealData3 = (TextView) findViewById(R.id.tvDealData3);
        tvDealData4 = (TextView) findViewById(R.id.tvDealData4);
        tvDealData5 = (TextView) findViewById(R.id.tvDealData5);

    }

    private void initData() {
        Observable<String> observable = Observable.just(saySomeThing());
        observable.observeOn(AndroidSchedulers.mainThread())
                .map(upperLetterFunc)
                .subscribe(textAction);

//        Observable<String> observableMap = Observable.from(wordList);
//        observableMap.observeOn(AndroidSchedulers.mainThread()).map(upperLetterFunc).subscribe(textAction);
//        Observable.just(stringList)
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(oneLetterFunc)
//                .reduce(mergeStrinFunc)
//                .subscribe(toastAction);

    }

    private String saySomeThing() {
        return "Hello, My name is fan ya feng!";
    }

    //更新ui控件
    private Action1<String> textAction = new Action1<String>() {
        @Override
        public void call(String s) {
            tvDealData.setText(tvDealData.getText().toString() + s);
        }
    };

    private Action1<IpInfoBean> ipInfoBeanAction1 = new Action1<IpInfoBean>() {
        @Override
        public void call(IpInfoBean ipInfoBean) {
            tvDealData1.setText(ipInfoBean.country);
        }
    };

    private Action1<String> toastAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(RxDealDataActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    };

    private Action1<IpInfoBean> ipInfoToastAction = ipInfoBean -> Toast.makeText(RxDealDataActivity.this, ipInfoBean.country, Toast.LENGTH_SHORT).show();

    private Func1<List<String>, Observable<String>> oneLetterFunc = new Func1<List<String>, Observable<String>>() {
        @Override
        public Observable<String> call(List<String> strings) {
            return Observable.from(strings);
        }
    };

    private Func1<List<IpInfoBean>, Observable<IpInfoBean>> getIpInfoFunc = ipInfoBeen -> Observable.from(ipInfoBeen);

//    private Func1<List<List<IpInfoBean>>,Observable<IpInfoBean>> ipInfoBeanObservableFunc1=new Func1<List<List<IpInfoBean>>, Observable<IpInfoBean>>() {
//        @Override
//        public Observable<IpInfoBean> call(List<List<IpInfoBean>> lists) {
//            return Observable.just(lists);
//        }
//    };

    private Func1<List<IpInfoBean>, Observable<IpInfoBean>> ipInfoFunc = new Func1<List<IpInfoBean>, Observable<IpInfoBean>>() {
        @Override
        public Observable<IpInfoBean> call(List<IpInfoBean> ipInfoBeen) {
            return Observable.from(ipInfoBeen);
        }
    };

    //处理数据
    private Func1<String, String> upperLetterFunc = s -> s.toUpperCase();

    private Func2<String, String, String> mergeStrinFunc = new Func2<String, String, String>() {
        @Override
        public String call(String s, String s2) {
            return String.format("%s %s", s, s2);
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnDealOperate:
                testIpInfo();
                break;
        }
    }

    private void testIpInfo() {
        ipInfoBeanList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            IpInfoBean ipInfoBean = new IpInfoBean();
            ipInfoBean.setCountry("中国北京" + i);
            ipInfoBeanList.add(ipInfoBean);
        }
        Observable.just(ipInfoBeanList)
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())//默认在主线程，但是如果需要在主线程却在子线程定义的会报错
                .flatMap(new Func1<List<IpInfoBean>, Observable<IpInfoBean>>() {
                    @Override
                    public Observable<IpInfoBean> call(List<IpInfoBean> ipInfoBeen) {
                        return Observable.from(ipInfoBeen);
                    }
                })
                .filter(new Func1<IpInfoBean, Boolean>() {//过滤器
                    @Override
                    public Boolean call(IpInfoBean ipInfoBean) {
                        return ipInfoBean.getCountry().equals("中国北京0");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//决定subscribe线程
                .flatMap(new Func1<IpInfoBean, Observable<IpInfoBean>>() {
                    @Override
                    public Observable<IpInfoBean> call(IpInfoBean ipInfoBean) {
                        return Observable.just(ipInfoBean);
                    }
                })
                .subscribe(new Action1<IpInfoBean>() {

                    @Override
                    public void call(IpInfoBean i) {
                        Toast.makeText(RxDealDataActivity.this, i.country, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void operate() {
        ipInfoBeanList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            IpInfoBean ipInfoBean = new IpInfoBean();
            ipInfoBean.setCountry("中国北京" + i);
            ipInfoBeanList.add(ipInfoBean);
        }
        Observable.just(ipInfoBeanList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.newThread())默认在主线程，但是如果需要在主线程却在子线程定义的会报错
                .flatMap(getIpInfoFunc)
                .filter(new Func1<IpInfoBean, Boolean>() {//过滤器
                    @Override
                    public Boolean call(IpInfoBean ipInfoBean) {
                        return ipInfoBean.getCountry().equals("中国北京0");
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(ipInfoToastAction);
    }
}
