package com.example.quantsappdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.quantsappdemo.app.App;
import com.example.quantsappdemo.core.MainActivityPresenter;
import com.example.quantsappdemo.databinding.ActivityMainBinding;
import com.example.quantsappdemo.di.DaggerMainActivityComponent;
import com.example.quantsappdemo.di.MainActivityModule;
import com.example.quantsappdemo.ui.main.SectionsPagerAdapter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View {

    ActivityMainBinding binding;
    private Context context;

    @Inject
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;
        injectDependency(); // dagger
        initView();

        setPageAdapter();


    }

    @Override
    public void initView() {

    }

    @Override
    public void injectDependency() {
        DaggerMainActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }


    @Override
    public void setPageAdapter() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        binding.viewPager.setAdapter(sectionsPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
    }
}