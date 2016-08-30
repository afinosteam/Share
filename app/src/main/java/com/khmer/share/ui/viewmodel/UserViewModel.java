package com.khmer.share.ui.viewmodel;

import android.databinding.BaseObservable;

import com.khmer.share.ui.model.User;

/**
 * @Copy Right 2012-2016, Afinos, Inc., or its affiliates
 * @Author: Afinos Team
 **/
public class UserViewModel extends BaseObservable {
    private final User model;

    public UserViewModel(User model) {
        this.model = model;
    }

    public String getName() {
        return model.getName();
    }

    public String getFirstName() {
        return model.getFirst_name();
    }

    public String getLastName() {
        return model.getLast_name();
    }

    public String getUrl() {
        return model.getPicture().getData().getUrl();
    }

    public User getModel() {
        return model;
    }
}
