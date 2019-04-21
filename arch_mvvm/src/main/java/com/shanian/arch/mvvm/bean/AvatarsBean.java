package com.shanian.arch.mvvm.bean;

public class AvatarsBean {
    /**
     * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.jpg
     * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.jpg
     * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.jpg
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}