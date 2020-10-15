package com.example.quantsappdemo;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.quantsappdemo.app.App;
import com.example.quantsappdemo.core.MainActivityPresenter;
import com.example.quantsappdemo.databinding.ActivityMainBinding;
import com.example.quantsappdemo.di.DaggerMainActivityComponent;
import com.example.quantsappdemo.di.MainActivityModule;
import com.example.quantsappdemo.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;



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


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        binding.viewPager.setAdapter(sectionsPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);

        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Toast.makeText(MainActivity.this, "tab: "+tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


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
}