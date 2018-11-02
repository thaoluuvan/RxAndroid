package com.example.framgialuuvanthao.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerViewColor;
    SimpleStringAdapter simpleStringAdapter;
    Observable<List<String>> tvShowObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        tvShowObservable = Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                Log.d(TAG, "onNext: " + Thread.currentThread().getName());
                return getColorList();
            }
        });

        tvShowObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Loaded data", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<String> tvShows) {
                        Log.d(TAG, "onNext: " + Thread.currentThread().getName());
                        simpleStringAdapter.setStrings(tvShows);
                    }
                });
    }

    private void initViews() {
        simpleStringAdapter = new SimpleStringAdapter(this);
        recyclerViewColor = findViewById(R.id.recyclerViewColor);
        recyclerViewColor.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewColor.setAdapter(simpleStringAdapter);
    }

    private static List<String> getColorList() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("thao handsome");
        colors.add("blue");
        colors.add("green");
        colors.add("red");
        colors.add("chartreuse");
        colors.add("Van Dyke Brown");
        return colors;
    }
}
