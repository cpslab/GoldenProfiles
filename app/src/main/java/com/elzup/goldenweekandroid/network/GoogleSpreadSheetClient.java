package com.elzup.goldenweekandroid.network;

import java.io.IOException;

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

    public Observable<Response> request(final String id) {
        return Observable.create(new Observable.OnSubscribe<Response>() {
            OkHttpClient client = new OkHttpClient();

            @Override
            public void call(Subscriber<? super Response> subscriber) {
                try {
                    String url = HOST + SPREADSHEETS_ENDPOINT + id + SPREADSHEETS_CSV_TAIL;
                    Response response = client.newCall(new Request.Builder().url(url).build()).execute();
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                    if (!response.isSuccessful()) subscriber.onError(new Exception("error"));
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
