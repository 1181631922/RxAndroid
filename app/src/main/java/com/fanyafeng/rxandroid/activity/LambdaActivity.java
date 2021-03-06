package com.fanyafeng.rxandroid.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.rxandroid.BaseActivity;
import com.fanyafeng.rxandroid.R;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class LambdaActivity extends BaseActivity {
    private TextView tvLambda;

    String[] manyWords = {"My", "name", "is","fanyafeng"};
    List<String> manyWordList = Arrays.asList(manyWords);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        title = "测试lambda表达式";
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
        tvLambda = (TextView) findViewById(R.id.tvLambda);
    }

    private void initData() {

    }

    private void setTvLambdaText() {
        Observable<String> observable = Observable.just(saySomething());
        observable.observeOn(AndroidSchedulers.mainThread())
                .map(String::toUpperCase)
                .subscribe(tvLambda::setText);//java8
    }

    private void setMapText() {
        Observable.just(manyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Observable::from)
                .reduce(this::mergeStrin)
                .subscribe(this::showToast);
    }


    private String saySomething() {
        return "Hello,I am your friend";
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private String mergeStrin(String s1, String s2) {
        return String.format("%s %s", s1, s2);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnSetText:
                setTvLambdaText();
                break;
            case R.id.btnSetMap:
                setMapText();
                break;
        }
    }
}
