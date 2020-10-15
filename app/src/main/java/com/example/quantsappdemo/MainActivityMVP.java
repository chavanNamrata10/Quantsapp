package com.example.quantsappdemo;

import com.example.quantsappdemo.global.GlobalModel;
import com.example.quantsappdemo.global.GlobalPresenter;
import com.example.quantsappdemo.global.GlobalView;

public interface MainActivityMVP {

    interface Model extends GlobalModel {
        boolean isNetworkAvailable();

    }

    interface View extends GlobalView {

    }

    interface Presenter extends GlobalPresenter {

    }
}
