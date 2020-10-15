package com.example.quantsappdemo.ui.fragment.tab2.core;

import com.example.quantsappdemo.NetworkUtils;
import com.example.quantsappdemo.api.API;
import com.example.quantsappdemo.ui.fragment.tab2.Fragment2MVP;

public class Fragment2Model implements Fragment2MVP.Model {

    private API api;
    private NetworkUtils networkUtils;

    public Fragment2Model(API api, NetworkUtils networkUtils) {
        this.api = api;
        this.networkUtils = networkUtils;
    }


    @Override
    public boolean isNetworkAvailable() {
        return networkUtils.isNetworkAvailable();
    }
}
