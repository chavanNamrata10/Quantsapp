package com.example.quantsappdemo.ui.fragment.tab1.di;


import com.example.quantsappdemo.builder.AppComponent;
import com.example.quantsappdemo.ui.fragment.tab1.TabFragment1;

import dagger.Component;

@Fragment1Scope
@Component(dependencies = {AppComponent.class}, modules = {Fragment1Module.class})
public interface Fragment1Component {
    void inject(TabFragment1 tabFragment1);
}
