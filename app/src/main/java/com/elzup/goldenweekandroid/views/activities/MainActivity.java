package com.elzup.goldenweekandroid.views.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.elzup.goldenweekandroid.adapters.GoldenUserAdapter;
import com.elzup.goldenweekandroid.managers.GoogleSpreadSheet;
import com.elzup.goldenweekandroid.R;
import com.elzup.goldenweekandroid.models.GoldenUser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import okhttp3.Response;
import rx.Subscriber;

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        GoogleSpreadSheet.client().request(getString(R.string.spreadsheet_id)).subscribe(new Subscriber<Response>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response response) {
                // response
                try {
                    String csvText = response.body().string();
                    List<GoldenUser> dayItems = opencsvToBean(csvText);
                    mAdapter = new GoldenUserAdapter(dayItems);
                    mRecyclerView.setAdapter(mAdapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static List<GoldenUser> opencsvToBean(String text) {
        List<GoldenUser> users = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new StringReader(text));
            reader.readNext();
            for (String[] params: reader.readAll()) {
                GoldenUser user = new GoldenUser();
                // ニックネーム,学籍番号,今期アニメ,好きなアニメ,好きな言語,画像
                user.setName(params[0]);
                user.setStudentID(params[1]);
                user.setCurrentAnime(params[2]);
                user.setFavoriteAnime(params[3]);
                user.setLanguage(params[4]);
                user.setImgURL(params[5]);
                users.add(user);
            }

            // NOTE: 謎の opencv Error
            // ColumnPositionMappingStrategy<GoldenUser> strat = new ColumnPositionMappingStrategy<>();
            // strat.setType(GoldenUser.class);
            // strat.setColumnMapping(HEADER);
            // CsvToBean<GoldenUser> csv = new CsvToBean<>();
            // return csv.parse(strat, reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }

}
