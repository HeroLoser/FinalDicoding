package com.bara.submission1.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private String name, username, company, location, repository, followers, following;
    private int avatar;

    public Data(String name, String username, String company, String location, String repository, String followers, String following, int avatar) {
        this.name = name;
        this.username = username;
        this.company = company;
        this.location = location;
        this.repository = repository;
        this.followers = followers;
        this.following = following;
        this.avatar = avatar;
    }

    protected Data(Parcel in) {
        name = in.readString();
        username = in.readString();
        company = in.readString();
        location = in.readString();
        repository = in.readString();
        followers = in.readString();
        following = in.readString();
        avatar = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(company);
        dest.writeString(location);
        dest.writeString(repository);
        dest.writeString(followers);
        dest.writeString(following);
        dest.writeInt(avatar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

}
