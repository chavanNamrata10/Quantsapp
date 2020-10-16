package com.example.quantsappdemo.ui.fragment.tab2.di;


import com.example.quantsappdemo.NetworkUtils;
import com.example.quantsappdemo.api.API;
import com.example.quantsappdemo.ui.fragment.tab2.Fragment2MVP;
import com.example.quantsappdemo.ui.fragment.tab2.TabFragment2;
import com.example.quantsappdemo.ui.fragment.tab2.core.Fragment2Model;
import com.example.quantsappdemo.ui.fragment.tab2.core.Fragment2Presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class Fragment2Module {

    TabFragment2 tabFragment2;

    public Fragment2Module(TabFragment2 tabFragment2) {
        this.tabFragment2 = tabFragment2;
    }

    @Fragment2Scope
    @Provides
    Fragment2Presenter providePresenter(Fragment2Model model) {
        return new Fragment2Presenter((Fragment2MVP.View) tabFragment2, model);
    }

    @Fragment2Scope
    @Provides
    Fragment2Model provideModel(API api, NetworkUtils networkUtils) {
        return new Fragment2Model(api, networkUtils);
    }

    @Fragment2Scope
    @Provides
    NetworkUtils provideNetworkUtils() {
        return new NetworkUtils(tabFragment2.getContext());
    }

}
