package com.example.quantsappdemo.api;

import com.example.quantsappdemo.response.FeedResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API {

     String BASE_URL = "https://api.androidhive.info/";


    @GET("feed/feed.json")
    Observable<FeedResponse> getFeedNews();
}
