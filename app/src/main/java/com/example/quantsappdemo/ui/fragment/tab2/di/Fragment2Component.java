package com.example.quantsappdemo.ui.fragment.tab2.di;


import com.example.quantsappdemo.builder.AppComponent;
import com.example.quantsappdemo.ui.fragment.tab2.TabFragment2;

import dagger.Component;

@Fragment2Scope
@Component(dependencies = {AppComponent.class}, modules = {Fragment2Module.class})
public interface Fragment2Component {
    void inject(TabFragment2 tabFragment2);
}
