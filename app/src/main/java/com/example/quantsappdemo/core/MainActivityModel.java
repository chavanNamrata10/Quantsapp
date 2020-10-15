package com.example.quantsappdemo.core;

import com.example.quantsappdemo.MainActivityMVP;
import com.example.quantsappdemo.NetworkUtils;
import com.example.quantsappdemo.api.API;


public class MainActivityModel implements MainActivityMVP.Model {

     API api;
    private NetworkUtils networkUtils;


    public MainActivityModel(API api, NetworkUtils networkUtils) {
        this.api = api;
        this.networkUtils = networkUtils;
    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }
}
