package com.example.framgialuuvanthao.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();
    private TextView txtDisplay;
    private Integer[] integers = {1, 2, 3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getANumberObservable()
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        Log.i(TAG, "Operator thread " + Thread.currentThread().getName());
                        return String.valueOf(integer);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "Subscriber thread " + Thread.currentThread().getName());
                    }
                });

    }

    private Observable<Integer> getANumberObservable() {
        return Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                Log.i(TAG, "Observable thread " + Thread.currentThread().getName());
                return Observable.just(1);
            }
        });
    }

    private void initViews() {
        txtDisplay = findViewById(R.id.txtDisplay);
    }
}
