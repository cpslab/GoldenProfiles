package com.elzup.goldenweekandroid;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoogleSpreadSheetClient {

    public Observable<Response> request() {
        return Observable.create(new Observable.OnSubscribe<Response>() {
            OkHttpClient client = new OkHttpClient();

            @Override
            public void call(Subscriber<? super Response> subscriber) {
                try {
                    String url = "https://docs.google.com/spreadsheets/d/1qCSHtAntOGwmYV3bCBy3JlrxUGqNP0DuTEJ_YvrVaxc/pub?output=csv";
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
