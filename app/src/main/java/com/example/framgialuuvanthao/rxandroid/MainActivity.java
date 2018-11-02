package com.example.framgialuuvanthao.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    TextView txtDisplay;
    Integer[] integers = {1, 2, 3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Observable.just(integers).subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            public void onNext(Integer[] integers) {
                txtDisplay.setText("" + integers);

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
                txtDisplay.setText("" + integer);
            }
        });

    }

    private void initViews() {
        txtDisplay = findViewById(R.id.txtDisplay);
    }
}
