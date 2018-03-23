package com.upitt.ruc.Views.NewsFeed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.upitt.ruc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by omkar on 3/23/2018.
 */

public class Post_Header extends RecyclerView.ViewHolder {

    @BindView(R.id.post_header_profile_pic)
    CircleImageView post_header_profile_pic;

    @BindView(R.id.post_header_description)
    EditText post_header_description;

    @BindView(R.id.post_header_photo_layout)
    LinearLayout post_header_photo_layout;

    @BindView(R.id.post_header_video_layout)
    LinearLayout post_header_video_layout;

    @BindView(R.id.post_header_audio_layout)
    LinearLayout post_header_audio_layout;

    @BindView(R.id.post_header_layout)
    RelativeLayout post_header_layout;

    @BindView(R.id.post_header_cancel)
    Button post_header_cancel;

    @BindView(R.id.post_header_post)
    Button post_header_post;


    public Post_Header(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
