package com.example.quantsappdemo.di;

import com.example.quantsappdemo.MainActivity;
import com.example.quantsappdemo.MainActivityMVP;
import com.example.quantsappdemo.NetworkUtils;
import com.example.quantsappdemo.api.API;
import com.example.quantsappdemo.core.MainActivityModel;
import com.example.quantsappdemo.core.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @MainActivityScope
    @Provides
    MainActivity provideContext() {
        return this.mainActivity;
    }


    @MainActivityScope
    @Provides
    MainActivityPresenter providePresenter(MainActivityModel model) {
        return new MainActivityPresenter(mainActivity, model);
    }


    @MainActivityScope
    @Provides
    MainActivityModel provideModel(API api, NetworkUtils networkUtils) {
        return new MainActivityModel(api,  networkUtils);
    }

    @MainActivityScope
    @Provides
    NetworkUtils provideNetworkUtil() {
        return new NetworkUtils(mainActivity);
    }


}
