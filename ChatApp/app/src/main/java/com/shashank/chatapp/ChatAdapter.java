package com.shashank.chatapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shashank.chatapp.databinding.ItemChatBinding;

import java.util.zip.Inflater;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by shashank on 09/05/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public final LayoutInflater inflater;
    public ResponseModel responseData;


    public ChatAdapter(Context context, final Observable<ResponseModel> data) {
        inflater = LayoutInflater.from(context);
        data.subscribe(new Action1<ResponseModel>() {
            @Override
            public void call(ResponseModel responseModel) {
                responseData = responseModel;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemChatBinding)DataBindingUtil.inflate(inflater,R.layout.item_chat,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).binding.setVm(responseData.messages.get(position));
    }

    @Override
    public int getItemCount() {
        return responseData ==null || responseData.messages==null?0:responseData.messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public final ItemChatBinding binding;

        public ViewHolder(ItemChatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
