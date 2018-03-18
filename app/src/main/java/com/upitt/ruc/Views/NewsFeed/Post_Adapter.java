package com.upitt.ruc.Views.NewsFeed;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.upitt.ruc.Entities.Post;
import com.upitt.ruc.R;

import java.util.ArrayList;

/**
 * Created by omkar on 3/17/2018.
 */

/********************** if possible add progress bars on all imageviews ***********/
public class Post_Adapter extends RecyclerView.Adapter {

    private final int POST_TEXT = 1;
    private final int POST_TEXT_AUDIO = 2;
    private final int POST_TEXT_VIDEO = 3;
    private final int POST_TEXT_IMAGE = 4;

    private ArrayList<Post> posts;
    Context context;
    Handler handler;
    Runnable runnable;
    ImageView m_play, m_pause;
    MediaPlayer m_mediaPlayer;

    public Post_Adapter(ArrayList<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {

        if ((posts.get(position).getFile_type()).equals("text"))
            return POST_TEXT;

        if ((posts.get(position).getFile_type()).equals("text_image"))
            return POST_TEXT_IMAGE;

        if ((posts.get(position).getFile_type()).equals("text_audio"))
            return POST_TEXT_AUDIO;

        if ((posts.get(position).getFile_type()).equals("text_video"))
            return POST_TEXT_VIDEO;


        throw new IllegalArgumentException(

                "we are being asked for an item type from position " + position + ", though we have no such item"

        );
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mainView;


        if (viewType == POST_TEXT) {
            mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_text, parent, false);
            return new Post_Text_Holder(mainView);
        } else if (viewType == POST_TEXT_AUDIO) {
            mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_text_audio, parent, false);
            return new Post_Text_Audio_Holder(mainView);
        } else if (viewType == POST_TEXT_IMAGE) {
            mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_text_image, parent, false);
            return new Post_Text_Image_Holder(mainView);
        } else if (viewType == POST_TEXT_VIDEO) {
            mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_text_video, parent, false);
            return new Post_Text_Video_Holder(mainView);
        } else
            throw new IllegalArgumentException("ViewType " + viewType + " is not supported");
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        RequestOptions options = new RequestOptions();
        options.centerCrop();

        if (holder instanceof Post_Text_Holder) {
            Post post = posts.get(position);

            Glide.with(context).load(post.getUser_profile_pic_URL()).into(((Post_Text_Holder) holder).post_text_profile_pic);

            ((Post_Text_Holder) holder).post_text_desc.setText(post.getDesc());
            ((Post_Text_Holder) holder).post_text_name.setText(post.getUser_name());
            ((Post_Text_Holder) holder).post_text_location.setText(post.getUser_location());

        } else if (holder instanceof Post_Text_Image_Holder) {
            Post post = posts.get(position);

            Glide.with(context).load(post.getUser_profile_pic_URL()).into(((Post_Text_Image_Holder) holder).post_text_image_profile_pic);

            ((Post_Text_Image_Holder) holder).post_text_image_desc.setText(post.getDesc());
            ((Post_Text_Image_Holder) holder).post_text_image_name.setText(post.getUser_name());
            ((Post_Text_Image_Holder) holder).post_text_image_location.setText(post.getUser_location());

            Glide.with(context).load(post.getResource_URL()).apply(options).into(((Post_Text_Image_Holder) holder).post_text_image_Imageview);
        } else if (holder instanceof Post_Text_Audio_Holder) {
            Post post = posts.get(position);

            Glide.with(context).load(post.getUser_profile_pic_URL()).into(((Post_Text_Audio_Holder) holder).post_text_audio_profile_pic);

            ((Post_Text_Audio_Holder) holder).post_text_audio_desc.setText(post.getDesc());
            ((Post_Text_Audio_Holder) holder).post_text_audio_name.setText(post.getUser_name());
            ((Post_Text_Audio_Holder) holder).post_text_audio_location.setText(post.getUser_location());

            final MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(post.getResource_URL());
                mediaPlayer.prepare();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    //Log.i("In","onPrepared");
                    //mediaPlayer.start();
                    ((Post_Text_Audio_Holder) holder).post_text_audio_scrubber.setMax(mediaPlayer.getDuration());
                    playCycle(((Post_Text_Audio_Holder) holder).post_text_audio_scrubber, mediaPlayer);
                }
            });

            handler = new Handler();

            ((Post_Text_Audio_Holder) holder).post_text_audio_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("test", "Here");
                    //mediaPlayer.start();
                    onPlay(((Post_Text_Audio_Holder) holder).post_text_audio_scrubber, position, mediaPlayer, ((Post_Text_Audio_Holder) holder).post_text_audio_play, ((Post_Text_Audio_Holder) holder).post_text_audio_pause);
                }
            });


            ((Post_Text_Audio_Holder) holder).post_text_audio_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onPause(mediaPlayer, ((Post_Text_Audio_Holder) holder).post_text_audio_play, ((Post_Text_Audio_Holder) holder).post_text_audio_pause);
                }
            });


        } else if (holder instanceof Post_Text_Video_Holder) {
            Post post = posts.get(position);

            Glide.with(context).load(post.getUser_profile_pic_URL()).into(((Post_Text_Video_Holder) holder).post_text_video_profile_pic);

            ((Post_Text_Video_Holder) holder).post_text_video_desc.setText(post.getDesc());
            ((Post_Text_Video_Holder) holder).post_text_video_name.setText(post.getUser_name());
            ((Post_Text_Video_Holder) holder).post_text_video_location.setText(post.getUser_location());

            ((Post_Text_Video_Holder) holder).post_text_video_videoView.setVideoPath(post.getResource_URL());
            ((Post_Text_Video_Holder) holder).post_text_video_videoView.setMediaController(new MediaController(context));
            //((Post_Text_Video_Holder) holder).post_text_video_videoView.start();
        }


    }


    public void playCycle(final SeekBar seekBar, final MediaPlayer mediaPlayer) {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle(seekBar, mediaPlayer);
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    public void onPlay(final SeekBar scrubber, int position, final MediaPlayer mediaPlayer, ImageView play, ImageView pause) {
        if (m_mediaPlayer != null && m_mediaPlayer.isPlaying()) {
            m_mediaPlayer.pause();
            m_pause.setVisibility(View.INVISIBLE);
            m_play.setVisibility(View.VISIBLE);

        }
        //mediaPlayer = MediaPlayer.create(context, posts.get(position).getAudio_id());
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser)
                    mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mediaPlayer.start();
        playCycle(scrubber, mediaPlayer);
        m_mediaPlayer = mediaPlayer;
        m_play = play;
        m_pause = pause;


        playCycle(scrubber, mediaPlayer);
        play.setVisibility(View.INVISIBLE);
        pause.setVisibility(View.VISIBLE);
    }

    public void onPause(MediaPlayer mediaPlayer, ImageView play, ImageView pause) {
        mediaPlayer.pause();
        pause.setVisibility(View.INVISIBLE);
        play.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
