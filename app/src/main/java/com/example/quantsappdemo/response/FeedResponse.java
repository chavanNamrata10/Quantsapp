package com.example.quantsappdemo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedResponse {

    @SerializedName("feed")
    @Expose
    private List<Feed> feed = null;

    public List<Feed> getFeed() {
        return feed;
    }

    public void setFeed(List<Feed> feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "FeedResponse{" +
                "feed=" + feed +
                '}';
    }
}
