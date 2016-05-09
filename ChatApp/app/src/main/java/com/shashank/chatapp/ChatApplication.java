package com.shashank.chatapp;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import rx.Observable;

/**
 * Created by shashank on 09/05/16.
 */
public class ChatApplication extends Application {

    private RequestQueue mRequestQueue;

    static ChatApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static ChatApplication getInstance() {
        return mInstance;
    }

    public synchronized  RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);
        }
        return mRequestQueue;
    }


}
