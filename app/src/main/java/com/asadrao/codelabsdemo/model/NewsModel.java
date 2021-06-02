package com.asadrao.codelabsdemo.model;

public class NewsModel {
    public String id;
    public String title;
    public String summary;
    public String link;
    public String published;

    public NewsModel(String id, String title, String summary, String link, String published) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.link = link;
        this.published = published;
    }
}
