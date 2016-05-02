package com.elzup.goldenprofile.network;

import com.elzup.goldenprofile.models.GoldenUser;
import com.elzup.goldenprofile.util.OpenCsvUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoogleSpreadSheetClient {

    private static String HOST = "https://docs.google.com";
    private static String SPREADSHEETS_ENDPOINT = "/spreadsheets/d/";
    private static String SPREADSHEETS_CSV_TAIL = "/pub?output=csv";

    public Observable<List<GoldenUser>> requestGoldenUsers(final String id) {
        return Observable.create(new Observable.OnSubscribe<List<GoldenUser>>() {
            OkHttpClient client = new OkHttpClient();

            @Override
            public void call(Subscriber<? super List<GoldenUser>> subscriber) {
                // レスポンスをもらったら呼ばれる場所1, response を受け取り GoldenUser のリストにして 次の subscribe に渡す
                try {
                    String url = HOST + SPREADSHEETS_ENDPOINT + id + SPREADSHEETS_CSV_TAIL;
                    Response response = client.newCall(new Request.Builder().url(url).build()).execute();
                    List<GoldenUser> users = OpenCsvUtil.toGoldenUsers(response.body().string());
                    subscriber.onNext(users);
                    subscriber.onCompleted();
                    if (!response.isSuccessful()) subscriber.onError(new Exception("error"));
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
