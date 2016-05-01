package com.elzup.goldenweekandroid.views.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.elzup.goldenweekandroid.R;
import com.elzup.goldenweekandroid.adapters.GoldenUserAdapter;
import com.elzup.goldenweekandroid.managers.GoogleSpreadSheet;
import com.elzup.goldenweekandroid.models.GoldenUser;

import java.util.List;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton reloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        GoogleSpreadSheet.client().requestGoldenUsers(getString(R.string.spreadsheet_id)).subscribe(new Subscriber<List<GoldenUser>>() {
            @Override public void onCompleted() { }
            @Override public void onError(Throwable e) { }
            @Override
            public void onNext(List<GoldenUser> users) {
                mAdapter = new GoldenUserAdapter(users);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        this.reloadButton = (FloatingActionButton) findViewById(R.id.reload_btn);
        reloadButton.hide();
        loadUsers();

        this.reloadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reloadButton.hide();
                loadUsers();
            }
        });
    }

    private void loadUsers() {
        GoogleSpreadSheet.client().requestGoldenUsers(getString(R.string.spreadsheet_id)).subscribe(new Subscriber<List<GoldenUser>>() {
            @Override public void onCompleted() { }
            @Override public void onError(Throwable e) { }
            @Override
            public void onNext(List<GoldenUser> users) {
                mAdapter = new GoldenUserAdapter(users);
                // NOTE: 怪しい
                mRecyclerView.setAdapter(mAdapter);
                reloadButton.show();
                mRecyclerView.notifyAll();
            }
        });
    }

}
