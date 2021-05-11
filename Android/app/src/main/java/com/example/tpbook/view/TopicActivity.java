package com.example.tpbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tpbook.databinding.ActivityRegisterBinding;
import com.example.tpbook.databinding.ActivityTopicBinding;
import com.example.tpbook.utils.Commons;

public class TopicActivity extends AppCompatActivity {
        ActivityTopicBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopicBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        System.out.println(Commons.topic);
    }
}