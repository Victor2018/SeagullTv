package com.victor.model.data;

import java.util.List;

/**
 * Created by victor on 2017/4/19.
 */
public class PageBean {
    private int allPages;
    private List<FunnyContentData> contentlist;

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public void setContentlist(List<FunnyContentData> contentlist) {
        this.contentlist = contentlist;
    }

    public int getAllPages() {

        return allPages;
    }

    public List<FunnyContentData> getContentlist() {
        return contentlist;
    }
}
