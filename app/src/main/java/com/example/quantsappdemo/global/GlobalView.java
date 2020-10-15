package com.example.quantsappdemo.global;

import android.content.Context;


public interface GlobalView {
    void initView();
    void injectDependency();
    void attachBaseContext(Context newBase);
//    void onConnectionChange(ConnectionLiveData connectionLiveData);
}
