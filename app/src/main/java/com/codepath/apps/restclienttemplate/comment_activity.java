package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.Objects;

public class comment_activity extends AppCompatActivity {
    CardView cvPostImage;
    ImageView ivPostImage;
    TextView tvCreatedAt;
    CardView cardView;
    ImageView ivProfileImage;
    TextView tvScreenName;
    TextView tvBody;
    TextView tvReplyMessage;
    EditText etComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("EXTRA_TWEET"));
        cvPostImage = findViewById(R.id.cvPostImage);
        ivPostImage = findViewById(R.id.ivPostImage);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        cardView = findViewById(R.id.cardView);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvScreenName = findViewById(R.id.tvScreenName);
        tvBody = findViewById(R.id.tvBody);
        tvReplyMessage = findViewById(R.id.tvReplyMessage);
        etComment = findViewById(R.id.etComment);

        tvBody.setText(tweet.body);
        tvCreatedAt.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
        tvScreenName.setText("@" + tweet.user.screenName);
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfileImage);
        if (!Objects.equals(tweet.userPostImageUrl, "")){
            Glide.with(this).load(tweet.userPostImageUrl).into(ivPostImage);
        } else{
            ivPostImage.setVisibility(View.GONE);
            cvPostImage.setVisibility(View.GONE);
        }
        tvReplyMessage.setText("Replying to @" + tweet.user.screenName);

    }
}