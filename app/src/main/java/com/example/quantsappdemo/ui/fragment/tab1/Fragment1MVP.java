package com.example.quantsappdemo.ui.fragment.tab1;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quantsappdemo.global.GlobalFragmentPresenter;
import com.example.quantsappdemo.global.GlobalFragmentView;
import com.example.quantsappdemo.global.GlobalModel;
import com.example.quantsappdemo.response.FeedResponse;

import java.util.List;

import io.reactivex.Observable;

public interface Fragment1MVP {

    interface Model extends GlobalModel {

        Observable<FeedResponse> getFeedNews();

    }


    interface View extends GlobalFragmentView {

        void hideNoNetworkView();

        void showNoNetworkView();

        void setFeedData(FeedResponse feedResponses);

        void setUpRecyclerview(RecyclerView recyclerview);
    }


    interface Presenter extends GlobalFragmentPresenter {

        void getFeed();
    }

}
