package com.upitt.ruc.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by omkar on 3/17/2018.
 */

public class Post implements Parcelable {

    private int post_id;
    private String desc;
    private String file_type;
    private String resource_URL;
    private int user_id;
    private int likes;
    private int dislikes;
    private String user_name;
    private String user_location;
    private String user_profile_pic_URL;

    public Post(int post_id, String desc, String file_type, String resource_URL, int user_id, int likes, int dislikes, String user_name, String user_location, String user_profile_pic_URL) {
        this.post_id = post_id;
        this.desc = desc;
        this.file_type = file_type;
        this.resource_URL = resource_URL;
        this.user_id = user_id;
        this.likes = likes;
        this.dislikes = dislikes;
        this.user_name = user_name;
        this.user_location = user_location;
        this.user_profile_pic_URL = user_profile_pic_URL;
    }

    public Post(Parcel in) {
        post_id = in.readInt();
        desc = in.readString();
        file_type = in.readString();
        resource_URL = in.readString();
        user_id = in.readInt();
        likes = in.readInt();
        dislikes = in.readInt();
        user_name = in.readString();
        user_location = in.readString();
        user_profile_pic_URL = in.readString();
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public void setResource_URL(String resource_URL) {
        this.resource_URL = resource_URL;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public void setUser_profile_pic_URL(String user_profile_pic_URL) {
        this.user_profile_pic_URL = user_profile_pic_URL;
    }

    public int getPost_id() {

        return post_id;
    }

    public String getDesc() {
        return desc;
    }

    public String getFile_type() {
        return file_type;
    }

    public String getResource_URL() {
        return resource_URL;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_location() {
        return user_location;
    }

    public String getUser_profile_pic_URL() {
        return user_profile_pic_URL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(post_id);
        parcel.writeString(desc);
        parcel.writeString(file_type);
        parcel.writeString(resource_URL);
        parcel.writeInt(user_id);
        parcel.writeInt(likes);
        parcel.writeInt(dislikes);
        parcel.writeString(user_name);
        parcel.writeString(user_location);
        parcel.writeString(user_profile_pic_URL);
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
