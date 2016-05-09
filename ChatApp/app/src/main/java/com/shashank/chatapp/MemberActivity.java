package com.shashank.chatapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.shashank.chatapp.databinding.ActivityMemberBinding;

import java.util.ArrayList;
import java.util.List;

public class MemberActivity extends AppCompatActivity {

    public ResponseModel data;

    public  List<MessageCount> countData;

    ActivityMemberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_member);
        setSupportActionBar(binding.toolbar);


        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {
            data = (ResponseModel)bundle.getSerializable("data");
            countData = parseResult();
        }

        bindUI();

    }

    public void bindUI() {
        binding.rvData.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.rvData.setAdapter(new MemberAdapter(this,countData));
    }


    public List<MessageCount> parseResult() {
        countData = new ArrayList<>();
        for (Message msg: data.messages) {
            boolean isFound = false;
            for (MessageCount c : countData) {
                if(msg.username.equalsIgnoreCase(c.userId)) {
                    isFound = true;
                    c.count++;
                    break;
                }
            }
            if(!isFound) {
                countData.add(new MessageCount(msg.username,msg.name));
            }
        }
        return countData;
    }


    public static class MessageCount {

        public final String userId;

        public final String userName;

        public int count;

        public MessageCount(String userId, String userName) {
            this.userId = userId;
            this.userName = userName;
            this.count = 1;
        }
    }

}
