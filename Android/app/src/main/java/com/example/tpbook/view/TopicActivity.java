package com.example.tpbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tpbook.databinding.ActivityRegisterBinding;
import com.example.tpbook.databinding.ActivityTopicBinding;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.utils.Commons;
import com.example.tpbook.view.MainPage.MainPageActivity;

import java.util.ConcurrentModificationException;

public class TopicActivity extends AppCompatActivity {
        ActivityTopicBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopicBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.customToolbar.toolbarTitle.setText("Đề tài");
        binding.customToolbar.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TopicActivity.this, MainPageActivity.class));
            }
        });

        setupData();
    }

    private void setupData() {
        binding.txtnameTopic.setText("Tên đề tài: "+Commons.topic.getTopicName());
        binding.txtDescription.setText("Mô tả: "+Commons.topic.getDescription());
        binding.txtstatus.setText("Trạng thái: "+Commons.topic.getStatus());

        if(Commons.student.getIdTopic()!=null || Commons.student.getIdTopic()!=""){
            binding.btnChoose.setVisibility(View.GONE);
        }
    }
}