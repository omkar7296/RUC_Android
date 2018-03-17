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

public class Post_Text_Image_Holder extends RecyclerView.ViewHolder {

    @BindView(R.id.post_text_image_cardView)
    CardView post_text_image_cardView;

    @BindView(R.id.post_text_image_profile_pic)
    ImageView post_text_image_profile_pic;

    @BindView(R.id.post_text_image_name)
    TextView post_text_image_name;

    @BindView(R.id.post_text_image_location)
    TextView post_text_image_location;

    @BindView(R.id.post_text_image_desc)
    TextView post_text_image_desc;

    @BindView(R.id.post_text_image_Imageview)
    ImageView post_text_image_Imageview;

    @BindView(R.id.post_text_image_like)
    ImageView post_text_image_like;

    @BindView(R.id.post_text_image_dislike)
    ImageView post_text_image_dislike;

    @BindView(R.id.post_text_image_comments_view)
    TextView post_text_image_comments_view;

    public Post_Text_Image_Holder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
