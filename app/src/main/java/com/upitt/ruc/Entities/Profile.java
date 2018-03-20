package com.upitt.ruc.Entities;

/**
 * Created by omkar on 3/10/2018.
 */

public class Profile {
    ////////// should be singleton

    private String name;
    private String email;
    private String profile_pic_url;
    private String location;
    private String lived;
    private String points;
    private String badge1_url;
    private String badge2_url;
    private String badge3_url;
    private String about_me;
    private String sports_activities;
    private String hobbies_interests;
    private String places;


    public Profile(String name, String profile_pic_url, String location, String lived, String points, String badge1_url, String badge2_url, String badge3_url, String about_me, String sports_activities, String hobbies_interests, String places, String email) {
        this.name = name;
        this.profile_pic_url = profile_pic_url;
        this.location = location;
        this.lived = lived;
        this.points = points;
        this.badge1_url = badge1_url;
        this.badge2_url = badge2_url;
        this.badge3_url = badge3_url;
        this.about_me = about_me;
        this.sports_activities = sports_activities;
        this.hobbies_interests = hobbies_interests;
        this.places = places;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public String getLocation() {
        return location;
    }

    public String getLived() {
        return lived;
    }

    public String getPoints() {
        return points;
    }

    public String getBadge1_url() {
        return badge1_url;
    }

    public String getBadge2_url() {
        return badge2_url;
    }

    public String getBadge3_url() {
        return badge3_url;
    }

    public String getAbout_me() {
        return about_me;
    }

    public String getSports_activities() {
        return sports_activities;
    }

    public String getHobbies_interests() {
        return hobbies_interests;
    }

    public String getPlaces() {
        return places;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLived(String lived) {
        this.lived = lived;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setBadge1_url(String badge1_url) {
        this.badge1_url = badge1_url;
    }

    public void setBadge2_url(String badge2_url) {
        this.badge2_url = badge2_url;
    }

    public void setBadge3_url(String badge3_url) {
        this.badge3_url = badge3_url;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public void setSports_activities(String sports_activities) {
        this.sports_activities = sports_activities;
    }

    public void setHobbies_interests(String hobbies_interests) {
        this.hobbies_interests = hobbies_interests;
    }

    public void setPlaces(String places) {
        this.places = places;
    }
}
