package com.upitt.ruc.Views.NewsFeed;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.upitt.ruc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omkar on 3/16/2018.
 */

public class Post_Text_Audio_Holder extends RecyclerView.ViewHolder {

    @BindView(R.id.post_text_audio_cardView)
    CardView post_text_audio_cardView;

    @BindView(R.id.post_text_audio_profile_pic)
    ImageView post_text_audio_profile_pic;

    @BindView(R.id.post_text_audio_name)
    TextView post_text_audio_name;

    @BindView(R.id.post_text_audio_location)
    TextView post_text_audio_location;

    @BindView(R.id.post_text_audio_play)
    ImageView post_text_audio_play;

    @BindView(R.id.post_text_audio_pause)
    ImageView post_text_audio_pause;

    @BindView(R.id.post_text_audio_scrubber)
    SeekBar post_text_audio_scrubber;

    @BindView(R.id.post_text_audio_desc)
    TextView post_text_audio_desc;

    @BindView(R.id.post_text_audio_like)
    ImageView post_text_audio_like;

    @BindView(R.id.post_text_audio_dislike)
    ImageView post_text_audio_dislike;

    @BindView(R.id.post_text_audio_comments_view)
    TextView post_text_audio_comments_view;

    public Post_Text_Audio_Holder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
