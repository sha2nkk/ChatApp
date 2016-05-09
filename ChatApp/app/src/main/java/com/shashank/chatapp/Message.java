package com.shashank.chatapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by shashank on 09/05/16.
 */
public class Message implements Serializable{


    @SerializedName("body")
    public final String body;

    @SerializedName("username")
    public final String username;

    @SerializedName("Name")
    public final String name;

    @SerializedName("image-url")
    public final String imageUrl;

    @SerializedName("message-time")
    public final String messageTime;

    public Message(String body, String username, String name, String imageUrl, String messageTime) {
        this.body = body;
        this.username = username;
        this.name = name;
        this.imageUrl = imageUrl;
        this.messageTime = messageTime;
    }
}
