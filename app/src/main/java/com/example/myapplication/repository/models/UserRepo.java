package com.example.myapplication.repository.models;

public class UserRepo {

    private String name;
    private String description;
    private String updatedAt;
    private int stargazers_count;
    private int forks;

    public UserRepo() {
    }

    public UserRepo(String name, String description, String updatedAt, int stargazers_count, int forks) {
        this.name = name;
        this.description = description;
        this.updatedAt = updatedAt;
        this.stargazers_count = stargazers_count;
        this.forks = forks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }
}
