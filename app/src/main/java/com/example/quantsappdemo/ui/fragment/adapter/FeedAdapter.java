package com.example.quantsappdemo.ui.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quantsappdemo.R;
import com.example.quantsappdemo.databinding.RowFeedAdapterBinding;
import com.example.quantsappdemo.response.Feed;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context context;
    private List<Feed> feedList;

    public FeedAdapter(Context context) {
        this.context = context;
        feedList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowFeedAdapterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.row_feed_adapter, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvName.setText(feedList.get(position).getName());
        holder.binding.tvStatus.setText(feedList.get(position).getStatus());
        holder.binding.tvUrl.setText(feedList.get(position).getUrl());
        Timestamp timestamp = new Timestamp(Long.parseLong(feedList.get(position).getTimeStamp()));
        Date date = new Date(timestamp.getTime());
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String date1 = dateFormat.format(date);
        holder.binding.tvTimestamp.setText(date1);

        feedList.get(position).loadImage(holder.binding.ivProfile, feedList.get(position).getProfilePic());

        feedList.get(position).loadImage(holder.binding.ivFeedImage, feedList.get(position).getImage());

    }



    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public void setData(List<Feed> feed) {
        feedList.clear();
        feedList.addAll(feed);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RowFeedAdapterBinding binding;

        public ViewHolder(RowFeedAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
