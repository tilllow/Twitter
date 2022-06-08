package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.w3c.dom.Text;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.Viewholder>{

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    Context context;
    List<Tweet> tweets;
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Get the data at position
        Tweet tweet = tweets.get(position);
        // Bind the data in the Viewholder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }
    // Pass in the context and list of tweets
    // For each row, inflate the layout
    // Bind values based on the position of the element

    // Define a Viewholder

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
        }


    }
}
