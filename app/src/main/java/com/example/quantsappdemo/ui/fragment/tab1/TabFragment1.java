package com.example.quantsappdemo.ui.fragment.tab1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quantsappdemo.R;
import com.example.quantsappdemo.app.App;
import com.example.quantsappdemo.databinding.Tabfragment1Binding;
import com.example.quantsappdemo.response.FeedResponse;
import com.example.quantsappdemo.ui.fragment.adapter.FeedAdapter;
import com.example.quantsappdemo.ui.fragment.tab1.core.Fragment1Presenter;
import com.example.quantsappdemo.ui.fragment.tab1.di.DaggerFragment1Component;
import com.example.quantsappdemo.ui.fragment.tab1.di.Fragment1Module;

import java.util.List;

import javax.inject.Inject;

public class TabFragment1 extends Fragment implements Fragment1MVP.View {

    Tabfragment1Binding binding;

    private View rootView;

    FeedAdapter feedAdapter;
    @Inject
    Fragment1Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependency();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tabfragment1, container, false);
        rootView = binding.getRoot();
        initView(rootView);


        presenter.onCreateView();

        setUpRecyclerview(binding.rvFeed);

        return rootView;
    }

    @Override
    public void initView(View rootView) {
    }

    @Override
    public void injectDependency() {
        DaggerFragment1Component.builder()
                .appComponent(App.getAppComponent())
                .fragment1Module(new Fragment1Module(this))
                .build()
                .inject(this);

    }

    @Override
    public void hideNoNetworkView() {

    }

    @Override
    public void showNoNetworkView() {
        Toast.makeText(getContext(), "No Internet Available. Please try again later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFeedData(FeedResponse feedResponses) {

        feedAdapter.setData(feedResponses.getFeed());
//        Toast.makeText(getContext(), "response: " + feedResponses, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUpRecyclerview(RecyclerView recyclerview) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        feedAdapter = new FeedAdapter(getActivity());
        recyclerview.setAdapter(feedAdapter);

    }


}
