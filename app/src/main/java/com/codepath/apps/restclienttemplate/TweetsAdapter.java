package com.codepath.apps.restclienttemplate;

import static com.codepath.apps.restclienttemplate.R.drawable.ic_vector_compose;
import static com.codepath.apps.restclienttemplate.R.drawable.vector_compose_dm_fab;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

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
        ImageView ivPhoto;
        TextView tvCreatedAt;
        ImageButton ibComment;
        ImageButton ibRetweet;
        ImageButton ibLike;
        CardView cvPostImage;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            ivPhoto = itemView.findViewById(R.id.ivPostImage);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            ibComment = itemView.findViewById(R.id.ibComment);
            ibRetweet = itemView.findViewById(R.id.ibRetweet);
            ibLike = itemView.findViewById(R.id.ibLike);
            cvPostImage = itemView.findViewById(R.id.cvPostImage);

        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvCreatedAt.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
            tvScreenName.setText("@" + tweet.user.screenName);
            ibComment.setBackgroundResource(vector_compose_dm_fab);
            ibRetweet.setBackgroundResource(R.drawable.ic_vector_retweet_stroke);
            ibLike.setBackgroundResource(R.drawable.ic_vector_heart_stroke);

            ibComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ibComment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context,comment_activity.class);
                            intent.putExtra("EXTRA_TWEET",Parcels.wrap(tweet));
                            context.startActivity(intent);
                        }
                    });
                }
            });

            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            if (!Objects.equals(tweet.userPostImageUrl, "")){
                Glide.with(context).load(tweet.userPostImageUrl).into(ivPhoto);
            } else{
                ivPhoto.setVisibility(View.GONE);
                cvPostImage.setVisibility(View.GONE);
            }
        }
    }
}
