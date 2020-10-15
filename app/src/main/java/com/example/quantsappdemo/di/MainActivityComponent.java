package com.example.quantsappdemo.di;



import com.example.quantsappdemo.MainActivity;
import com.example.quantsappdemo.builder.AppComponent;

import dagger.Component;

@MainActivityScope
@Component(dependencies = {AppComponent.class}, modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

}
