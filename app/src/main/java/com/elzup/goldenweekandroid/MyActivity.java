package com.elzup.goldenweekandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Subscriber;

/**
 * Created by hiro on 16/05/01.
 */
public class MyActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        GoogleSpreadSheet.client().request().subscribe(new Subscriber<Response>() {
                     @Override
                     public void onCompleted() {
                     }
                     @Override
                     public void onError(Throwable e) {

                     }
                    @Override
                    public void onNext(Response response) {
                        // response
                        ArrayList<DayItem> dayItems = new ArrayList<>();
                        mAdapter = new MyAdapter(dayItems);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
    }

}
