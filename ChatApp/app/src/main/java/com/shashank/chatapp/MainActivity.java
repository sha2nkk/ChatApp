package com.shashank.chatapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.shashank.chatapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    ChatWindowViewModel viewModel;

    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        bindUI();
    }

    private void bindUI() {
        if(viewModel == null ) {
            viewModel = new ChatWindowViewModel();
        }
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle("ChatApp");
        binding.setVm(viewModel);
        adapter = new ChatAdapter(this, viewModel.msgData.asObservable());
        binding.rvData.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.rvData.setAdapter(adapter);
        binding.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.msgData.getValue()!=null) {
                    Intent intent = new Intent(MainActivity.this, MemberActivity.class);
                    intent.putExtra("data", viewModel.msgData.getValue());
                    startActivity(intent);
                }
            }
        });

    }
}
