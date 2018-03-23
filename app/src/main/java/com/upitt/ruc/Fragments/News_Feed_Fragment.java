package com.upitt.ruc.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.upitt.ruc.Entities.Post;
import com.upitt.ruc.R;
import com.upitt.ruc.Views.NewsFeed.Post_Adapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omkar on 3/9/2018.
 */

public class News_Feed_Fragment extends Fragment {

    @BindView(R.id.posts_recyclerView)
    RecyclerView posts_recyclerView;
    private Context mContext;

    private ArrayList<Post> posts = new ArrayList<Post>();

    public static Fragment newInstance()
    {
        return new News_Feed_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_news_feed,container,false);

        mContext = rootView.getContext();
        ButterKnife.bind(this, rootView);


        posts.add(null);

        posts.add(new Post(1,
                "Here is a sample description",
                "text",
                "",
                1,
                20,
                30,
                "Omkar Sawant",
                "Pittsburgh",
                "https://s3.amazonaws.com/plonlinecontent/online/ronaldo7-1489414339"
        ));

        posts.add(new Post(2,
                "Here is a sample description",
                "text_image",
                "http://www.chelseafc.com/content/cfc/en/homepage/news/boilerplate-config/latest-news/2017/04/memberships-for-next-season-available-now.img.png",
                2,
                30,
                40,
                "Raghav Raman",
                "Pittsburgh",
                "https://s3.amazonaws.com/plonlinecontent/online/ronaldo7-1489414339"
        ));

        posts.add(new Post(2,
                "Here is a sample description",
                "text_audio",
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
                2,
                30,
                40,
                "Raghav Raman",
                "Pittsburgh",
                "https://s3.amazonaws.com/plonlinecontent/online/ronaldo7-1489414339"
        ));

        posts.add(new Post(3,
                "Here is a sample description",
                "text_video",
                "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
                2,
                30,
                40,
                "Omkar Sawant",
                "Pittsburgh",
                "https://s3.amazonaws.com/plonlinecontent/online/ronaldo7-1489414339"
        ));

        posts.add(new Post(3,
                "Here is a sample description",
                "text_video",
                "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
                2,
                30,
                40,
                "Omkar Sawant",
                "Pittsburgh",
                "https://s3.amazonaws.com/plonlinecontent/online/ronaldo7-1489414339"
        ));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        posts_recyclerView.setLayoutManager(linearLayoutManager);

        posts_recyclerView.setHasFixedSize(true);
        posts_recyclerView.setAdapter(new Post_Adapter(posts, mContext));


        return rootView;

    }
}
