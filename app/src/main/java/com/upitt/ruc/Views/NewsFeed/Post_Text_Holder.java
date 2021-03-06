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

public class Post_Text_Holder extends RecyclerView.ViewHolder {

    @BindView(R.id.post_text_cardView)
    CardView post_text_cardView;

    @BindView(R.id.post_text_profile_pic)
    ImageView post_text_profile_pic;

    @BindView(R.id.post_text_name)
    TextView post_text_name;

    @BindView(R.id.post_text_location)
    TextView post_text_location;

    @BindView(R.id.post_text_desc)
    TextView post_text_desc;

    @BindView(R.id.post_text_like)
    ImageView post_text_like;

    @BindView(R.id.post_text_dislike)
    ImageView post_text_dislike;

    @BindView(R.id.post_text_comments_view)
    TextView post_text_comments_view;

    public Post_Text_Holder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
