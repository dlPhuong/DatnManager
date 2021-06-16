package com.example.tpbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tpbook.databinding.ActivityReportBinding;
import com.example.tpbook.databinding.ActivityTopicBinding;
import com.example.tpbook.utils.Commons;

public class reportActivity extends AppCompatActivity {
    ActivityReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupData();
    }

    private void setupData() {
//        binding.txtnameTopic.setText(Commons.topic.getTopicName());
//        binding.txtDescription.setText(Commons.topic.getDescription());
//        binding.txtstatus.setText(Commons.topic.getStatus());
    }
}