package com.hao.androidrecord.activity.webviewOptimize.bean;

import java.util.List;

public class WebImage {

    public ImageUrl origin;
    public ImageUrl webp_origin;
    public ImageUrl thumb;
    public ImageUrl webp_thumb;

    public static class ImageUrl{
        public String uri;
        public List<String> urls;
    }


}
