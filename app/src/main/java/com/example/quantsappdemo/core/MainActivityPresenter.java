package com.example.quantsappdemo.core;


import com.example.quantsappdemo.MainActivity;
import com.example.quantsappdemo.MainActivityMVP;

import io.reactivex.disposables.Disposable;

public class MainActivityPresenter implements MainActivityMVP.Presenter {
    private MainActivityMVP.View view;
    private MainActivityModel model;
    private Disposable disposable;


    public MainActivityPresenter(MainActivityMVP.View view, MainActivityModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
