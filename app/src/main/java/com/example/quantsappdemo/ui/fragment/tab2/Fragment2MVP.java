package com.example.quantsappdemo.ui.fragment.tab2;

import android.webkit.WebView;

import com.example.quantsappdemo.global.GlobalFragmentPresenter;
import com.example.quantsappdemo.global.GlobalFragmentView;
import com.example.quantsappdemo.global.GlobalModel;

public interface Fragment2MVP {

    interface Model extends GlobalModel {
    }

    interface View extends GlobalFragmentView{

        void setUpWebview(WebView webView);



    }

    interface Presenter extends GlobalFragmentPresenter {
    }

}
