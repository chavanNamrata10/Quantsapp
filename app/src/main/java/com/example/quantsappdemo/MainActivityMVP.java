package com.example.quantsappdemo;

import com.example.quantsappdemo.global.GlobalModel;
import com.example.quantsappdemo.global.GlobalPresenter;
import com.example.quantsappdemo.global.GlobalView;
import com.google.android.material.tabs.TabLayout;

public interface MainActivityMVP {

    interface Model extends GlobalModel {
        boolean isNetworkAvailable();

    }

    interface View extends GlobalView {


        void setPageAdapter();
    }

    interface Presenter extends GlobalPresenter {

    }
}
