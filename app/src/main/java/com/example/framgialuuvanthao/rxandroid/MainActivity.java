package com.example.framgialuuvanthao.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerViewColor;
    SimpleStringAdapter simpleStringAdapter;
    Single<List<String>> tvShowSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        tvShowSingle= Single.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return getColorList();
            }
        });

        tvShowSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleSubscriber<List<String>>() {
            @Override
            public void onSuccess(List<String> tvShows) {
                simpleStringAdapter.setStrings(tvShows);
            }

            @Override
            public void onError(Throwable error) {

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
