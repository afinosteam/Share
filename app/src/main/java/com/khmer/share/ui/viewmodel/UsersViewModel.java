package com.khmer.share.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khmer.share.ui.model.User;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Copy Right 2012-2016, Afinos, Inc., or its affiliates
 * @Author: Afinos Team
 **/
public class UsersViewModel extends BaseObservable {
    public ObservableList<UserViewModel> items;

    public UsersViewModel() {
        this.items = new ObservableArrayList<>();
    }

    public void addItem(UserViewModel userViewModel) {
        this.items.add(userViewModel);
    }

    public UsersViewModel setItems(Collection<User> users) {
        this.items.clear();
        UserViewModel userViewModel;
        for (User user : users) {
            userViewModel = new UserViewModel(user);
            this.items.add(userViewModel);
        }
        return this;
    }

    public void sort(final boolean descending) {
        Collections.sort(this.items, new Comparator<UserViewModel>() {
            @Override
            public int compare(UserViewModel a, UserViewModel b) {
                if (descending)
                    return b.getName().compareTo(a.getName());
                else
                    return a.getName().compareTo(b.getName());
            }
        });
    }

    public void setUsers(JSONArray jsonArray) {
        Type listType = new TypeToken<ArrayList<User>>() {
        }.getType();
        List<User> users = new Gson().fromJson(jsonArray.toString(), listType);
        setItems(users);
    }

    public void addUsers(JSONArray data) {
        Type listType = new TypeToken<ArrayList<User>>() {
        }.getType();
        List<User> users = new Gson().fromJson(data.toString(), listType);
        addItems(users);
    }

    public UsersViewModel addItems(List<User> users) {
        UserViewModel userViewModel;
        for (User user : users) {
            userViewModel = new UserViewModel(user);
            this.items.add(userViewModel);
        }
        return this;
    }
}
