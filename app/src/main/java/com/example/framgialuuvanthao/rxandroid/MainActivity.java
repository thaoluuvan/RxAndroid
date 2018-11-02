package com.example.framgialuuvanthao.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Integer[] integers = {1, 2, 3};

        Observable.just(integers).subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            public void onNext(Integer[] integers) {
                Log.i("onNext", Arrays.toString(integers));
            }
        });

        Observable.from(integers).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            public void onNext(Integer integer) {
                Log.i("onNext", String.valueOf(integer));
            }
        });

    }
}
