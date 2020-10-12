package com.cmoney.testlab.model;

public class SinglePicture {
    private int id;
    private String content = "test content";
    private String thumbUrl;
    private String url;

    public SinglePicture(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getUrl() {
        return url;
    }
}
