package com.example.quantsappdemo.ui.fragment.tab1.di;


import com.example.quantsappdemo.NetworkUtils;
import com.example.quantsappdemo.api.API;
import com.example.quantsappdemo.ui.fragment.tab1.TabFragment1;
import com.example.quantsappdemo.ui.fragment.tab1.core.Fragment1Model;
import com.example.quantsappdemo.ui.fragment.tab1.core.Fragment1Presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class Fragment1Module {
    TabFragment1 tabFragment1;

    public Fragment1Module(TabFragment1 tabFragment1) {
        this.tabFragment1 = tabFragment1;
    }

    @Fragment1Scope
    @Provides
    Fragment1Presenter providePresenter(Fragment1Model model) {
        return new Fragment1Presenter(tabFragment1, model);

    }

    @Fragment1Scope
    @Provides
    Fragment1Model provideModel(API api, NetworkUtils networkUtils) {
        return new Fragment1Model(api, networkUtils);
    }

    @Fragment1Scope
    @Provides
    NetworkUtils provideNetworkUtil() {
        return new NetworkUtils(tabFragment1.getContext());
    }

}
