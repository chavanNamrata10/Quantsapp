package com.example.quantsappdemo.app;

import android.app.Application;
import android.content.Context;

import com.example.quantsappdemo.builder.AppComponent;
import com.example.quantsappdemo.builder.AppContextModule;
import com.example.quantsappdemo.builder.DaggerAppComponent;
import com.example.quantsappdemo.ui.fragment.tab2.WebAppInterface;


public class App extends Application {

    private static AppComponent appComponent;
    WebAppInterface appInterface;


    @Override
    public void onCreate() {
        super.onCreate();

        appInterface = new WebAppInterface(this);

        //AppComponent Dagger
        appComponent = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .build();
    }


    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
