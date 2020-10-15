package com.example.quantsappdemo.global;

import android.view.View;

public interface GlobalFragmentView {
    void initView(View rootView);
    void injectDependency();
}
