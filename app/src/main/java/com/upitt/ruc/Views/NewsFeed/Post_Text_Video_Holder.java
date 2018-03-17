package com.upitt.ruc.Views.NewsFeed;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.upitt.ruc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omkar on 3/16/2018.
 */

public class Post_Text_Video_Holder extends RecyclerView.ViewHolder {

    @BindView(R.id.post_text_video_cardView)
    CardView post_text_video_cardView;

    @BindView(R.id.post_text_video_profile_pic)
    ImageView post_text_video_profile_pic;

    @BindView(R.id.post_text_video_name)
    TextView post_text_video_name;

    @BindView(R.id.post_text_video_location)
    TextView post_text_video_location;

    @BindView(R.id.post_text_video_desc)
    TextView post_text_video_desc;

    @BindView(R.id.post_text_video_videoView)
    ImageView post_text_video_videoView;

    @BindView(R.id.post_text_video_like)
    ImageView post_text_video_like;

    @BindView(R.id.post_text_video_dislike)
    ImageView post_text_video_dislike;

    @BindView(R.id.post_text_image_comments_view)
    TextView post_text_video_comments_view;

    public Post_Text_Video_Holder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
