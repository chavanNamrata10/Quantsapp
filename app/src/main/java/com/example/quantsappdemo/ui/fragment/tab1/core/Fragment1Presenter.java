package com.example.quantsappdemo.ui.fragment.tab1.core;

import com.example.quantsappdemo.response.FeedResponse;
import com.example.quantsappdemo.rxjava_util.DisposableManager;
import com.example.quantsappdemo.ui.fragment.tab1.Fragment1MVP;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Fragment1Presenter implements Fragment1MVP.Presenter {

    private Fragment1MVP.View view;

    private Fragment1Model model;


    public Fragment1Presenter(Fragment1MVP.View view, Fragment1Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onCreateView() {
        try {

            if (model.isNetworkAvailable()) {
                view.hideNoNetworkView(); // hide no_network_view
                getFeed();
            } else {
                view.showNoNetworkView();
            }

        } catch (Exception e) {

        }
    }

    @Override
    public void onActivityCreated() {

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

    @Override
    public void onDetach() {

    }

    @Override
    public void getFeed() {
        try {

            model.getFeedNews().safeSubscribe(new Observer<FeedResponse>() {
                @Override
                public void onSubscribe(Disposable d) {
                    DisposableManager.add(d);
                }

                @Override
                public void onNext(FeedResponse feedResponse) {
                    if (feedResponse != null) {
                        view.setFeedData(feedResponse);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
            /*model.getFeedNews().subscribe(new Observer<List<FeedResponse>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    DisposableManager.add(d);
                }

                @Override
                public void onNext(List<FeedResponse> feedResponses) {
                    if (feedResponses != null) {
                        view.setFeedData(feedResponses);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
