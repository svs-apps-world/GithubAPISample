package com.example.myapplication.repository.models;

public class UserDetails {
    private String name;
    private String url;
    private String avatar_url;

    public UserDetails(String name, String url, String avatar_url) {
        this.name = name;
        this.url = url;
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
