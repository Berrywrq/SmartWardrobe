package com.android.ui;

/**
 * The Model of clothes
 * Created by HarryWang on 2017/4/30.
 */

public class Clothes {
    private String photo;
    private String type;
    private String color;
    private String keywords;
    private String season;
    private String upordown;
    private float rating;
    private int likematch;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getLikematch() {
        return likematch;
    }

    public void setLikematch(int likematch) {
        this.likematch = likematch;
    }

    public String getUpordown() {
        return upordown;
    }

    public void setUpordown(String upordown) {
        this.upordown = upordown;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }





}
