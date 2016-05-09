package com.shashank.chatapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shashank.chatapp.databinding.ItemMemberBinding;

import java.util.List;

/**
 * Created by shashank on 09/05/16.
 */
public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHodler> {

    List<MemberActivity.MessageCount> countData;
    LayoutInflater inflater;

    public MemberAdapter(Context context, List<MemberActivity.MessageCount> data) {
        inflater = LayoutInflater.from(context);
        countData  = data;
    }

    @Override
    public MemberViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemberViewHodler((ItemMemberBinding)DataBindingUtil.inflate(inflater,R.layout.item_member,parent,false));
    }

    @Override
    public void onBindViewHolder(MemberViewHodler holder, int position) {
        holder.binding.setVm(countData.get(position));
    }

    @Override
    public int getItemCount() {
        return countData.size();
    }

    public class MemberViewHodler extends RecyclerView.ViewHolder {

        ItemMemberBinding binding;

        public MemberViewHodler(ItemMemberBinding viewBinding) {
            super(viewBinding.getRoot());
            binding = viewBinding;
        }
    }
}
