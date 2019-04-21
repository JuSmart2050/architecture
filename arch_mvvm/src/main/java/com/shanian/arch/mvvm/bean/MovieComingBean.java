package com.shanian.arch.mvvm.bean;

import java.util.List;

public class MovieComingBean {


    /**
     * title : 即将上映的电影
     * total : 60
     * entries : [{"rating":"","pubdate":"2019-03-12","title":"小石头下乡记","wish":99,"original_title":"小石头下乡记","collection":8,"orignal_title":"小石头下乡记","stars":"00","images":{"large":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2548772681.jpg","small":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2548772681.jpg","medium":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2548772681.jpg"},"id":"30461812"}]
     */

    private String title;
    private int total;
    private List<EntriesBean> entries;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<EntriesBean> getEntries() {
        return entries;
    }

    public void setEntries(List<EntriesBean> entries) {
        this.entries = entries;
    }

}
