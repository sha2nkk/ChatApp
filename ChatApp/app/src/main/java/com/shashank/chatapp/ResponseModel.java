package com.shashank.chatapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shashank on 09/05/16.
 */
public class ResponseModel implements Serializable{

    @SerializedName("count")
    final int count;

    @SerializedName("messages")
    final List<Message> messages;

    public ResponseModel(int count, List<Message> messages) {
        this.count = count;
        this.messages = messages;
    }
}
