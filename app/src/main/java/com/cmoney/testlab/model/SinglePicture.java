package com.cmoney.testlab.model;

import com.google.gson.annotations.SerializedName;

public class SinglePicture {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String content = "test content";
    @SerializedName("thumbnailUrl")
    private String thumbUrl;
    @SerializedName("url")
    private String url;

    public SinglePicture(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
