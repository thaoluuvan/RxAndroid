package com.example.framgialuuvanthao.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity {
    public Observable<List<String>> listObservable = Observable.just(getColorList());
    public static String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerViewColor;
    SimpleStringAdapter simpleStringAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        listObservable.subscribe(new Observer<List<String>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<String> colors) {
                simpleStringAdapter.setStrings(colors);
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
        colors.add("blue");
        colors.add("green");
        colors.add("red");
        colors.add("chartreuse");
        colors.add("Van Dyke Brown");
        return colors;
    }
}
