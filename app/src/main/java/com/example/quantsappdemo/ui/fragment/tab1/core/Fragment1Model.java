package com.example.quantsappdemo.ui.fragment.tab1.core;

import com.example.quantsappdemo.NetworkUtils;
import com.example.quantsappdemo.api.API;
import com.example.quantsappdemo.response.FeedResponse;
import com.example.quantsappdemo.ui.fragment.tab1.Fragment1MVP;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Fragment1Model implements Fragment1MVP.Model {

    private API api;
    private NetworkUtils networkUtils;


    public Fragment1Model(API api, NetworkUtils networkUtils) {
        this.api = api;
        this.networkUtils = networkUtils;
    }

    @Override
    public boolean isNetworkAvailable() {
        return networkUtils.isNetworkAvailable();
    }

    @Override
    public Observable<FeedResponse> getFeedNews() {
        return api.getFeedNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

