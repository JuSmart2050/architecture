package com.shanian.arch.mvvm.bean;

public class EntriesBean {
    /**
     * rating :
     * pubdate : 2019-03-12
     * title : 小石头下乡记
     * wish : 99
     * original_title : 小石头下乡记
     * collection : 8
     * orignal_title : 小石头下乡记
     * stars : 00
     * images : {"large":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2548772681.jpg","small":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2548772681.jpg","medium":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2548772681.jpg"}
     * id : 30461812
     */

    private String rating;
    private String pubdate;
    private String title;
    private int wish;
    private String original_title;
    private int collection;
    private String orignal_title;
    private String stars;
    private AvatarsBean images;
    private String id;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWish() {
        return wish;
    }

    public void setWish(int wish) {
        this.wish = wish;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public String getOrignal_title() {
        return orignal_title;
    }

    public void setOrignal_title(String orignal_title) {
        this.orignal_title = orignal_title;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public AvatarsBean getImages() {
        return images;
    }

    public void setImages(AvatarsBean images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}