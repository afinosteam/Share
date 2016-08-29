package com.khmer.share.app;

import android.app.Application;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

/**
 * @Copy Right 2012-2016, Afinos, Inc., or its affiliates
 * @Author: Afinos Team
 **/
public class App extends Application {
    private static App instance;

    private AccessToken accessToken;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        instance = this;
    }

    public static App init() {
        return instance;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
