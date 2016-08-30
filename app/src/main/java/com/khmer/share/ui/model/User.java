package com.khmer.share.ui.model;

/**
 * @Copy Right 2012-2016, Afinos, Inc., or its affiliates
 * @Author: Afinos Team
 **/
public class User {
    private String id;
    private String name;
    private String first_name;
    private String last_name;
    private UserPicture picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public UserPicture getPicture() {
        return picture;
    }

    public void setPicture(UserPicture picture) {
        this.picture = picture;
    }


    public static class UserPicture {
        private Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    public static class Data {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}